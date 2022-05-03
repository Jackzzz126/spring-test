package org.jack.rock.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileSysStorageService
 */
@Service
@Slf4j
public class FileSysStorageService implements StorageService {
    private Path uploadPath;

    @Value("${sys.file-upload.path}")
    private String uploadPathStr;

    @Override
    public void init() throws IOException {
        this.uploadPath = Paths.get(this.uploadPathStr);

        Files.createDirectories(uploadPath);
    }

    @Override
    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (file.isEmpty()) {
            log.warn("empty file found");
            return;
        }
        if (fileName.contains("..")) {
            log.warn("file has relative path");
            return;
        }
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, this.uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public List<String> listAll() {
        try {
            return Files.walk(this.uploadPath, 1)
                    .filter(path -> !path.equals(this.uploadPath))
                    .map(this.uploadPath::relativize)
                    .map(path -> path.toString())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("list file error: ", e);
            return null;
        }
    }

    public Path load(String filename) {
        return uploadPath.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws IOException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("faild when read file");
            }
        } catch (Exception e) {
            throw new IOException("faild when read file");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.uploadPath.toFile());
    }
}
