package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.service.S3UploadService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@EzySingleton
@AllArgsConstructor
@Slf4j
public class S3UploadServiceImpl implements S3UploadService {

    private final S3Uploader s3Uploader;

    @Override
    public String uploadImageToS3(RequestArguments requestArguments, long userId) throws ServletException, IOException {
        HttpServletRequest request = requestArguments.getRequest();
        // Check if the request is a multipart request
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Invalid request type. Expected multipart request.");
        }

        // Get the part from the request
        Part part = request.getPart("file");

        // Check if the part exists and has content
        if (part == null || part.getSize() == 0) {
            throw new ServletException("Missing content for multipart request.");
        }

        String fileNameSubmit = part.getSubmittedFileName();
        return s3Uploader.putToS3(part.getInputStream(), fileNameSubmit);
    }

    @Override
    public String uploadCvToS3(RequestArguments requestArguments, long userId, long agencyId)
            throws ServletException, IOException {
        HttpServletRequest request = requestArguments.getRequest();
        // Check if the request is a multipart request
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Invalid request type. Expected multipart request.");
        }

        // Get the part from the request
        Part part = request.getPart("file");

        // Check if the part exists and has content
        if (part == null || part.getSize() == 0) {
            throw new ServletException("Missing content for multipart request.");
        }

        String fileNameSubmit = part.getSubmittedFileName();
        return s3Uploader.putToS3(part.getInputStream(), fileNameSubmit);
    }
}
