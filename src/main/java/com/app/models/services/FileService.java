package com.app.models.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {
  private final String PATH;
  private final File file;

  public FileService(String path) {
    PATH = path;
    file = new File(PATH);
    createFileOnPath();
  }

  public ArrayList<String> readFile() throws FileNotFoundException {
    ArrayList<String> result = new ArrayList();

    Scanner reader = new Scanner(file);

    while (reader.hasNextLine()) {
      result.add(reader.nextLine());
    }
    reader.close();
    return result;
  }

  public void writeFile(String[] input) {
    try {
      PrintStream printStream = new PrintStream(file);
      for (String s : input) {
        printStream.println(s);
      }
      printStream.close();

    } catch (FileNotFoundException e) {
      System.out.println("couldn't find file at: " + PATH); //TODO Refactor
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
        System.out.println("Invalid path"); // TODO: Refactor
      }
    }
  }
}
