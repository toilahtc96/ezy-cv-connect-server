package com.ezyfox.cvconnect.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EzySingleton
@AllArgsConstructor
@NoArgsConstructor
public class S3Uploader {
    @EzyProperty("amazon.s3.accessKey")
    private String accessKey;
    @EzyProperty("amazon.s3.secretKey")
    private String secretKey;
    @EzyProperty("amazon.s3.bucketName")
    private String bucketName;

    public String putToS3(InputStream inputStream, String fileName) {
        AmazonS3 s3Client = getAmazonS3(accessKey, secretKey);
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, null));
            String imageUrl = s3Client.getUrl(bucketName, fileName).toString();
            log.info("File uploaded successfully. link {}", imageUrl);
            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to upload file.");
            return "";
        }
    }

    public static void downloadFromS3(
            String accessKey,
            String secretKey,
            String bucketName,
            String downloadPath,
            String fileName
    ) {
        AmazonS3 s3Client = getAmazonS3(accessKey, secretKey);

        try {
            S3Object s3Object = s3Client.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();

            File downloadFile = new File(downloadPath);
            try (FileOutputStream outputStream = new FileOutputStream(downloadFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                log.info("File downloaded successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to download file.");
        }
    }

    public static void cloneFolder(
            S3Client s3Client,
            String sourceBucket,
            String sourceFolder,
            String destinationBucket,
            String destinationFolder
    ) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(sourceBucket)
                .build();

        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
        List<software.amazon.awssdk.services.s3.model.S3Object> objects = listObjectsResponse.contents()
                .stream().filter(item -> item.key().contains(sourceFolder)).collect(Collectors.toList());

        for (software.amazon.awssdk.services.s3.model.S3Object object : objects) {

            String sourceKey = object.key();
            String destinationKey = destinationFolder + sourceKey.substring(sourceFolder.length());

            CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                    .copySource(sourceBucket + "/" + sourceKey)
                    .destinationBucket(destinationBucket)
                    .destinationKey(destinationKey)
                    .build();

            try {
                CopyObjectResponse copyObjectResponse = s3Client.copyObject(copyObjectRequest);
                log.info("Object cloned successfully. ETag: " + copyObjectResponse.copyObjectResult().eTag());
            } catch (S3Exception e) {
                log.error("Error cloning object: " + e.getMessage());
            }
        }
    }

    private static AmazonS3 getAmazonS3(String accessKey, String secretKey) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("ap-southeast-1")
                .build();
    }

    public long getContentLength(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byteArrayOutputStream.flush();
        byte[] data = byteArrayOutputStream.toByteArray();
        return data.length;
    }

}
