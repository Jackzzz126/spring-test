package org.jack.rock.util;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * StorageService
 */
public interface StorageService {
    void init() throws IOException;

    void store(MultipartFile file) throws IOException;

    List<String> listAll();

    Resource loadAsResource(String filename) throws IOException;

    void deleteAll();
}
