package me.yonghong.springboot.lab.kotlininaction.chapter02;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;

import java.io.File;

public class FileTest {

    public static void main(String[] args) {
        File file = new File("build.gradle");
        // readText 拓展函数
        System.out.println(FilesKt.readText(file, Charsets.UTF_8));
    }
}
