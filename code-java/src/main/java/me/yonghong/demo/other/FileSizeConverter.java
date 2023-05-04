package me.yonghong.demo.other;

public class FileSizeConverter {

    public static void main(String[] args) {
        System.out.println(convertFileSize(1234567890L));
        System.out.println(convertFileSize(123456789L));
    }

    public static Pair<Double, String> convertFileSize(long fileSize) {
        if (fileSize < 0) {
            throw new IllegalArgumentException("文件大小不能为负数");
        }

        final int KB = 1024;
        final int MB = KB * 1024;
        final int GB = MB * 1024;

        double convertedSize;
        String unit;

        if (fileSize < KB) {
            convertedSize = fileSize;
            unit = "B";
        } else if (fileSize < MB) {
            convertedSize = (double) fileSize / KB;
            unit = "K";
        } else if (fileSize < GB) {
            convertedSize = (double) fileSize / MB;
            unit = "M";
        } else {
            convertedSize = (double) fileSize / GB;
            unit = "G";
        }

        return new Pair<>(Double.parseDouble(String.format("%.1f", convertedSize)), unit);
    }

    public static class Pair<F, S> {
        public final F first;
        public final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}
