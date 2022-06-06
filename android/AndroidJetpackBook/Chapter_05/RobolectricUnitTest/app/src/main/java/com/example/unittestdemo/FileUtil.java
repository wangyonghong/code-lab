package com.example.unittestdemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtil {

    public boolean writeStringFile(String str, String path) {
        BufferedOutputStream outputStream = null;
        try {
            File file = new File(path);
            File parentFile = file.getParentFile();
            if ((parentFile != null && !parentFile.exists()) || (parentFile != null && parentFile.isFile())) {
                if (!parentFile.mkdirs()) {
                    return false;
                }
            }
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    return false;
                }
            }
            if (!file.createNewFile()) {
                return false;
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }

    public String readStringFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            BufferedInputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] bytes = new byte[2048];
                StringBuilder sb = new StringBuilder();
                int count = 0;
                while ((count = inputStream.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, count, Charset.forName("UTF-8")));
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
