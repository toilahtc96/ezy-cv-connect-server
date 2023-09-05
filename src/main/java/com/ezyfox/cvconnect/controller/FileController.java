package com.ezyfox.cvconnect.controller;

import com.amazonaws.services.s3.transfer.Upload;
import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.request.UploadRequest;
import com.ezyfox.cvconnect.service.ProgressService;
import com.ezyfox.cvconnect.service.S3UploadService;
import com.ezyfox.cvconnect.service.impl.FileService;
import com.ezyfox.cvconnect.service.impl.FileUploadService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller("api/v1/upload")
@AllArgsConstructor
@Authenticated
public class FileController {

    private final FileUploadService fileUploadService;
    private final S3UploadService s3UploadService;
    private static final String PREFIX_USER_AVATAR = "avatar";
    private static final String PREFIX_JOB_THUMBNAIL = "job_thumbnail";
    private final FileService fileService;
    private final ProgressService progressService;

    private static final String NO_IMAGE = "D:\\dev\\line task 3\\ezy-cv-connect-server" +
            "\\src\\main\\resources\\assets\\no-image\\no-image.png";

    @DoPost("/upload-avatar")
    public ResponseEntity uploadUserAvatar(RequestArguments requestArguments, @UserId long userId) throws Exception {
        String imgUrl = s3UploadService.uploadImageToS3(requestArguments, userId);
        return ResponseEntity.ok(imgUrl);
    }

    @Async
    @DoPost("/upload-job-thumbnail")
    public ResponseEntity uploadJobThumbnail(RequestArguments requestArguments, @UserId long userId) throws Exception {
        fileUploadService.accept(
                requestArguments.getRequest(),
                requestArguments.getResponse(),
                userId,
                PREFIX_JOB_THUMBNAIL);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-single-image")
    public ResponseEntity getImage(HttpServletResponse response, @RequestParam("imagePath") String imagePath)
            throws IOException {
        byte[] imageBytes = fileService.readFully(new FileInputStream(new File(imagePath)), -1, true);
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("image/png");
        }
        return ResponseEntity.ok();

    }

    @DoGet("/get-no-image")
    public ResponseEntity getNoImage(HttpServletResponse response) throws IOException {
        byte[] imageBytes = fileService.readFully(new FileInputStream(NO_IMAGE), -1, true);
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("image/png");
        }
        return ResponseEntity.ok();

    }

    @DoPost("/upload-cv")
    public ResponseEntity uploadCv(
            RequestArguments requestArguments,
            @UserId long userId,
            @RequestParam("uploadRequest") String uploadRequestBody
    ) throws Exception {
        long agencyId = 1;
        String imgUrl = s3UploadService.uploadCvToS3(requestArguments, userId, agencyId);
//        progressService.updateCvLink(uploadRequestBody.getProgressId(), imgUrl);
        return ResponseEntity.ok(imgUrl);
    }
}
