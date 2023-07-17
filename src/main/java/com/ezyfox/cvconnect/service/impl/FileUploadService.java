package com.ezyfox.cvconnect.service.impl;

import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.resources.FileUploader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@EzySingleton
@AllArgsConstructor
@Slf4j
public class FileUploadService {
    @EzyProperty("user.avatar.dir")
    private static String userAvatarDir;

    public static String USER_DIR = System.getProperty("user.dir");
    private final FileUploader fileUploader;

    public void accept(HttpServletRequest request, HttpServletResponse response, long userId, String prefix)
        throws Exception {
        acceptUserAvatar(
            userId,
            prefix,
            request,
            response,
            request.getPart("file")
        );
    }

    public void acceptUserAvatar(
        long userId,
        String prefix,
        HttpServletRequest request,
        HttpServletResponse response,
        Part part
    ) {
        String fileNameSubmit = part.getSubmittedFileName();
        String extensionFile = getExtensionByStringHandling(fileNameSubmit).orElse("png");
        StringBuilder fileName = getFileName(userId, prefix);
        Path path = Paths.get(USER_DIR + userAvatarDir + buildUploadAvatarFolderName(userId));
        File file = new File(path + "\\" + fileName + "." + extensionFile);
        String addressOfFile = path + "\\" + fileName + "." + extensionFile;
        AsyncContext asyncContext = request.getAsyncContext();
        fileUploader.accept(asyncContext, part, file, () -> response.getWriter().print(addressOfFile));
    }

    private StringBuilder getFileName(long userId, String prefix) {
        StringBuilder fileName = new StringBuilder();
        LocalDateTime localDateTime = LocalDateTime.now();
        fileName
            .append(prefix)
            .append("_")
            .append(userId)
            .append("_")
            .append(localDateTime.getYear())
            .append(String.format("%02d", localDateTime.getMonthValue()))
            .append(localDateTime.getDayOfMonth())
            .append("_")
            .append(String.format("%02d", localDateTime.getHour()))
            .append(String.format("%02d", localDateTime.getMinute()))
            .append(String.format("%02d", localDateTime.getSecond()));
        return fileName;
    }

    private String buildUploadAvatarFolderName(long userId) {
        LocalDate localDate = LocalDate.now();
        StringBuilder timeFolderName = new StringBuilder();
        timeFolderName.append("/").append(userId).append("/");
        timeFolderName
            .append(localDate.getYear())
            .append(String.format("%02d", localDate.getMonthValue()))
            .append(localDate.getDayOfMonth()).append("/");
        return timeFolderName.toString();
    }

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}