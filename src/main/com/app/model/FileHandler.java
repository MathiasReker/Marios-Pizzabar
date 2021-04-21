package com.app.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
  private final String PATH;
  private final File file;
  private Scanner reader;

  FileHandler(String path) {
    PATH = path;
    file = new File(PATH);
    createFileOnPath();
  }

  ArrayList<String> readFile() {
    ArrayList<String> result = new ArrayList<>();
    try {
      reader = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("couldn't find file at: " + PATH);
    }
    {
      while (reader.hasNextLine()) {
        result.add(reader.nextLine());
      }
      reader.close();
    }

    return result;
  }

  void writeFile(ArrayList<String> input) {
    try {
      PrintStream printStream = new PrintStream(file);
      for (String s : input) {
        printStream.println(s);
      }
      printStream.close();

    } catch (FileNotFoundException e) {
      System.out.println("couldn't find file at: " + PATH);
    }
  }

  private void createFileOnPath() {
    if (!file.exists()) {
      String[] subPaths = PATH.split("/");
      StringBuilder dirPath = new StringBuilder();
      for (int i = 0; i < subPaths.length - 1; i++) {
        dirPath.append(subPaths[i]).append("/");
      }
      File dirs = new File(dirPath.toString());
      dirs.mkdirs();
      try {
        file.createNewFile();
      } catch (IOException e) {
        System.out.println("Invalid path");
      }
    }
  }
}
