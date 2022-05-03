package org.jack.rock.rest;

import org.jack.rock.util.Result;
import org.jack.rock.util.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FileUploadRest
 */
@RestController
public class FileUploadRest {
    private final StorageService storageService;

    public FileUploadRest(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("file-upload/list")
    public Result<List<String>> listUploadedFiles() {
        List<String> fileNames = storageService.listAll();
        return Result.success(fileNames);
    }

    @PostMapping("file-upload/upload")
    public Result<String> uploadFiles(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.store(file);
        return Result.success();
    }

    @GetMapping("file-upload/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
