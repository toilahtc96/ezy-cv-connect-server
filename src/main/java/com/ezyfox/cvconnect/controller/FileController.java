package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.service.impl.FileUploadService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller("api/v1/upload")
@AllArgsConstructor
@Authenticated
public class FileController {

    private final FileUploadService fileUploadService;
    private static final String PREFIX_USER_AVATAR = "avatar";
    private static final String PREFIX_JOB_THUMBNAIL = "job_thumbnail";

    private static final String NO_IMAGE = "D:\\dev\\line task 3\\ezy-cv-connect-server" +
            "\\src\\main\\resources\\assets\\no-image\\no-image.png";

    @Async
    @DoPost("/upload-avatar")
    public ResponseEntity uploadUserAvatar(RequestArguments requestArguments, @UserId long userId) throws Exception {
        fileUploadService.accept(
            requestArguments.getRequest(),
            requestArguments.getResponse(),
            userId,
            PREFIX_USER_AVATAR);
        return ResponseEntity.noContent();
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
        byte[] imageBytes = IOUtils.readFully(new FileInputStream(new File(imagePath)), -1, true);
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("image/png");
        }
        return ResponseEntity.ok();

    }

    @DoGet("/get-no-image")
    public ResponseEntity getNoImage(HttpServletResponse response) throws IOException {
        byte[] imageBytes = IOUtils.readFully(new FileInputStream(new File(NO_IMAGE)), -1, true);
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("image/png");
        }
        return ResponseEntity.ok();

    }
}
