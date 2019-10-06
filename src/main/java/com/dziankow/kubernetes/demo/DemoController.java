package com.dziankow.kubernetes.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class DemoController {
    @Value("${BOOKS_FILE_PATH}")
    private String booksFilePath;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Data
    static class Book {
        private String title;
        private String[] authors;
    }

    @GetMapping("/books")
    public List<Book> getBooks() throws IOException {
        log.info("Get books from " + booksFilePath);

        File booksFile = new File(booksFilePath);
        if (booksFile.exists()) {
            return MAPPER.readValue(booksFile, new TypeReference<List<Book>>() {});
        } else {
            return Arrays.asList();
        }
    }
}
