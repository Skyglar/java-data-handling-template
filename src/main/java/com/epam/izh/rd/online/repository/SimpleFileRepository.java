package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    private final String RESOURCES = "src/main/resources/";

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File(RESOURCES + path);
        long count = 0;

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                count += countFilesInDirectory(path + "/" + files[i].getName());
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long count = 0;

        try {
            count = Files.find(
                    Paths.get(RESOURCES + path),
                    10,  // how deep do we want to descend
                    (src, attributes) -> attributes.isDirectory()
            ).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File dir = new File(from);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".txt"));

        try {
            for (File file: files) {
                Reader reader = new FileReader(file);
                Writer writer = new BufferedWriter(
                        new FileWriter(new File(to + "/" + file.getName())));

                int data;
                while ((data = reader.read()) != -1) {
                    writer.write(data);
                }

                reader.close();
                writer.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        String absolutePath = RESOURCES + path;
        try {
            Path p = Paths.get(absolutePath);
            boolean isDirExists = Files.exists(p);

            if (!isDirExists) {
                new File(absolutePath).mkdirs();
            }

            if (isDirExists) {
                return new File(String.format("%s%s/%s", RESOURCES, path, name)).createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String from = String.format("src/main/resources/%s", fileName);
        StringBuilder str = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(from));

            String line;
            while ((line = reader.readLine()) != null ) {
                str.append(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
