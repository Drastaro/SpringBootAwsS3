package com.aws.s3.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Service
public class FileService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;

    public URL createFile(MultipartFile multipartFile) {
        URL url = null;

        var filename = multipartFile.getOriginalFilename();
        var fileName = StringUtils.cleanPath(filename);
        var objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(multipartFile.getSize());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, multipartFile
                    .getInputStream(), objectMetaData).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
            url = amazonS3Client.getUrl(bucketName, fileName);


        } catch (IOException e) {
            /* Handle Exception */
        }

        return url;
    }
}
