package me.yonghong.springboot.lab.javainaction.chapter01;

import java.io.File;
import java.io.FileFilter;

public class FileUtils {

    public static File[] listHiddenFiles() {
        // new
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        // old
        File[] hiddenFiles2 = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        return hiddenFiles;
    }

    public static void main(String[] args) {
        listHiddenFiles();
    }
}
