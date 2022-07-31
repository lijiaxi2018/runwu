package com.springstarter.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileSystemStorage {
    boolean saveFile(MultipartFile file) throws IOException;
    File loadFile(String fileName) throws FileNotFoundException;
    boolean moveFile(String originalPath, String newPath);
}
