package me.yonghong.demo.retrace;

import org.apache.commons.lang3.StringUtils;
import proguard.retrace.ReTrace;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RetraceUtils {
    public static void retrace(String text) throws IOException {
        ReTrace reTrace = new ReTrace(new File("mapping.txt"));
        text = text
                // 容错处理
                .replace("（", "(")
                .replace("）", ")")
                .replace("：", ":")
                // 提取关键部分
                .replace(")", ")\n");
        String[] split = text.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String trim = split[i].trim();
            if (StringUtils.isBlank(trim) || !trim.contains("com.qq")) {
                continue;
            }
            stringBuilder.append("at ").append(trim).append("\n");
        }
        text = stringBuilder.toString();
        System.out.println(text);
        LineNumberReader lineNumberReader = new LineNumberReader(
                new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)))));
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        reTrace.retrace(lineNumberReader, printWriter);
        String s = stringWriter.toString();
        System.out.println(s);
    }
}
