package com.springstarter.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import lombok.Data;
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
    public boolean moveFile(String originalPath, String newPath){
        File f = new File(uploadPath + location + originalPath);
        if(!f.exists()) {
            System.out.println("file not existing!");
            return false;
        }

        return f.renameTo(new File(uploadPath + location + newPath));
    }
}
