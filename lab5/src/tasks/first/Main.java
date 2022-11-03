package tasks.first;

import java.io.*;
import java.util.Random;

public class Main {
    private static final int ARRAY_LENGTH = 10000000;
    private static final String NORMAL_FILE_NAME = new File("./files/normal.dat").getAbsolutePath();
    private static final String BUFFERED_FILE_NAME = new File("./files/buffered.dat").getAbsolutePath();

    public static void main(String[] args) {
        Random random = new Random();
        byte[] dataToWrite = new byte[ARRAY_LENGTH];
        random.nextBytes(dataToWrite);
        try {
            long normalWriteDifference = writeToFileInNormalWay(dataToWrite);
            long bufferedWriteDifference = writeToFileInBufferedWay(dataToWrite);
            System.out.println("Разница: " + (normalWriteDifference - bufferedWriteDifference) + " мс");
            long normalReadDifference = readDataNormalWay();
            long bufferedReadDifference = readDataBufferedWay();
            System.out.println("Разница: " + (normalReadDifference - bufferedReadDifference) + " мс");
        }
        catch (Exception ignored) {}
    }

    private static long readDataNormalWay() throws IOException {
        long normalReadingStart = System.currentTimeMillis();
        FileInputStream normalFileIn = new FileInputStream(NORMAL_FILE_NAME);
        int availableBytes = normalFileIn.available();
        byte[] dataToRead = new byte[availableBytes];
        int count = normalFileIn.read(dataToRead, 0, availableBytes);
        normalFileIn.close();
        long normalReadingEnd = System.currentTimeMillis();
        long normalReadingDifference = normalReadingEnd - normalReadingStart;
        System.out.println("Время нормального чтения: " + normalReadingDifference + " мс");
        return normalReadingDifference;
    }

    private static long readDataBufferedWay() throws IOException {
        long bufferedReadingStart = System.currentTimeMillis();
        FileInputStream bufferedFileIn = new FileInputStream(BUFFERED_FILE_NAME);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(bufferedFileIn);
        int availableBytes = bufferedFileIn.available();
        byte[] dataToRead = new byte[availableBytes];
        int count = bufferedInputStream.read(dataToRead, 0, availableBytes);
        bufferedFileIn.close();
        long bufferedReadingEnd = System.currentTimeMillis();
        long bufferedReadingDifference = bufferedReadingEnd - bufferedReadingStart;
        System.out.println("Время буферезированного чтения: " + bufferedReadingDifference + " мс");
        return bufferedReadingDifference;
    }

    private static long writeToFileInNormalWay(byte[] dataToWrite) throws IOException {
        long normalWritingStart = System.currentTimeMillis();
        FileOutputStream normalFileOut = new FileOutputStream(NORMAL_FILE_NAME);
        normalFileOut.write(dataToWrite);
        normalFileOut.close();
        long normalWritingEnd = System.currentTimeMillis();
        long normalWritingDifference = normalWritingEnd - normalWritingStart;
        System.out.println("Время нормальной записи: " + normalWritingDifference + " мс");
        return normalWritingDifference;
    }

    private static long writeToFileInBufferedWay(byte[] dataToWrite) throws IOException {
        long bufferedWritingStart = System.currentTimeMillis();
        FileOutputStream bufferedFileOut = new FileOutputStream(BUFFERED_FILE_NAME);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(bufferedFileOut);
        bufferedOutputStream.write(dataToWrite);
        bufferedOutputStream.close();
        long bufferedWritingEnd = System.currentTimeMillis();
        long bufferedWritingDifference = bufferedWritingEnd - bufferedWritingStart;
        System.out.println("Время буферезированной записи: " + bufferedWritingDifference + " мс");
        return bufferedWritingDifference;
    }
}
