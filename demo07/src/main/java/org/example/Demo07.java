package org.example;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * FileChannel 文件零拷贝测试
  */

public class Demo07 {

    private static String srcFilePath = "D:\\download\\快点104部S+A版权短剧\\1-60\\01错有持花梦\\1.mp4";
    private static String descFilePath = "D:\\download\\快点104部S+A版权短剧\\1-60\\01错有持花梦\\111111.mp4";

    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();

//        normalCopy();
        zeroCopy();

        final long endTime = System.currentTimeMillis();
        float execTime = (endTime - startTime);
        System.out.println(execTime);
    }


    public static void normalCopy() throws IOException {
        final InputStream inputStream = new FileInputStream(srcFilePath);
        final OutputStream outputStream = new FileOutputStream(descFilePath);

        final byte[] buffer = new byte[1024 * 4];
        while (inputStream.read(buffer) >= 0) {
            outputStream.write(buffer);
        }

        outputStream.flush();
    }

    public static void zeroCopy() throws IOException {
        File srcFile = new File(srcFilePath);
        File descFile = new File(descFilePath);

        FileChannel srcFileChannel = new RandomAccessFile(srcFile, "r").getChannel();
        FileChannel descFileChannel = new RandomAccessFile(descFile, "rw").getChannel();
        srcFileChannel.transferTo(0, srcFile.length(), descFileChannel);
    }
}