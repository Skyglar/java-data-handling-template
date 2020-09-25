package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;

import java.io.*;

public class Main {
    public static void main(String... args) throws IOException{
        final String resources = "src/main/resources/";
        final String TEST_DIR_CREATE_PATH = "testDirCreateFile";
        final String TEST_FILE_TO_CREATE = "newFile.txt";
        final String TEST_DIR_COUNT_PATH = "testDirCountFiles";
        String to = String.format("%stestDirCountFiles/dir1", resources);
        String copy1 = String.format("%sGuardians of the Galaxy.mp3", resources);
        String copy2 = String.format("%sGuardians of the Galaxy2.mp3", resources);
        File dir = new File(resources);

        SimpleFileRepository repo = new SimpleFileRepository();
        //repo.countFilesInDirectory(TEST_DIR_COUNT_PATH);
        repo.createFile(TEST_DIR_CREATE_PATH, TEST_FILE_TO_CREATE);












        /*Runnable runnable = () -> {
            long beforeStream = System.currentTimeMillis();
            fileStreamCopy(song, copy2);
            long afterStream = System.currentTimeMillis();
            System.out.println("FileStream copy time is " + (afterStream - beforeStream) + " milliseconds");
        };

        Thread newThread = new Thread(runnable);
        newThread.start();

        long beforeBuffered = System.currentTimeMillis();
        bufferedCopy(song, copy1);
        long afterBuffered = System.currentTimeMillis();
        System.out.println("BufferedStream copy time is " + (afterBuffered - beforeBuffered) + " milliseconds");*/
    }

    public static void bufferedCopy(String inputFile, String outputFile) {
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile));

            //byte array that stores songs bytes
            byte[] temp = new byte[input.available()];
            int b, i = 0;

            while ((b = input.read()) != -1) {
                temp[i++] = (byte)b;
            }

            BufferedOutputStream output = new BufferedOutputStream(
                    new FileOutputStream(outputFile));

            output.write(temp);

            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileStreamCopy(String inputFile, String outputFile) {
        try {
            FileInputStream input = new FileInputStream(inputFile);

            byte[] temp = new byte[input.available()];
            int data, i = 0;

            while ((data = input.read()) != -1) {
                temp[i++] = (byte) data;
            }

            FileOutputStream output = new FileOutputStream(outputFile);

            output.write(temp);

            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
