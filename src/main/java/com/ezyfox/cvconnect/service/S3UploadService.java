package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.annotation.UserId;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;

import javax.servlet.ServletException;
import java.io.IOException;


public interface S3UploadService {
    String uploadImageToS3(RequestArguments requestArguments, @UserId long userId)
            throws ServletException, IOException;

    String uploadCvToS3(RequestArguments requestArguments, long userId, long agencyId)
            throws ServletException, IOException;
}
