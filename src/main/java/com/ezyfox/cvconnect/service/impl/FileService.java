package com.ezyfox.cvconnect.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

@EzySingleton
@AllArgsConstructor
public class FileService {
    public byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    public byte[] readFully(InputStream inputStream, int length, boolean detectPrematureEof)
            throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        int totalBytesRead = 0;
        while (totalBytesRead < length && (bytesRead = inputStream.read(buffer, 0,
                Math.min(buffer.length, length - totalBytesRead))) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
        }
        if (detectPrematureEof && totalBytesRead < length) {
            throw new EOFException("Detect premature EOF");
        }
        return outputStream.toByteArray();
    }
}
