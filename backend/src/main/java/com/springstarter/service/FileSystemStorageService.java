package com.springstarter.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import lombok.Data;
import nonapi.io.github.classgraph.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Data
@Service
public class FileSystemStorageService implements IFileSystemStorage{
    private String uploadPath;

    @Value("${file.upload.location}")
    private String location;


    public FileSystemStorageService() {
        this.uploadPath = System.getProperty("user.dir");
    }

    @Override
    public boolean saveFile(MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        String filename = file.getOriginalFilename();
        String filepath = uploadPath + location + filename;

        File dest = new File(filepath);
        file.transferTo(dest);
        return true;
    }

    @Override
    public File loadFile(String filePath) throws FileNotFoundException {
        // TODO Auto-generated method stub

        File f = new File(uploadPath + location + filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(uploadPath + location + filePath);
        }
        return f;
    }

    @Override
    public boolean moveFile(String originalPath, String newPath) {
        File f = new File(uploadPath + location + originalPath);
        if(!f.exists()) {
            System.out.println("file not existing!");
            return false;
        }

        return f.renameTo(new File(uploadPath + location + newPath));
    }

    @Override
    public boolean copyFile(String originalPath, String newPath) throws IOException {

        File source = new File(uploadPath + location + originalPath);
        File dest = new File(uploadPath + location + newPath);
        System.out.println(uploadPath + location);
        if (!source.exists()) {
            System.out.println("source not found");
        }
//
//        if (!dest.exists()) {
//            System.out.println("dest not found");
//        }

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }

        }

        return true;
    }
}
