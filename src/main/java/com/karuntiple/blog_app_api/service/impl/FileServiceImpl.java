package com.karuntiple.blog_app_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karuntiple.blog_app_api.service.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // File name
        String name = file.getOriginalFilename();
        // abc.png

        // Random name generate file
        String randomId = UUID.randomUUID().toString();

        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        // Full path
        String filePath = path + File.separator + fileName;

        // create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);

        // do logic to return
        return is;
    }

}


