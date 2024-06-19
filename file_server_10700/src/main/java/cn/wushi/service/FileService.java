package cn.wushi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    void uploadFile(MultipartFile file, String bucketName, String objectName) throws Exception;
    InputStream downloadFile(String bucketName, String objectName) throws Exception;
}
