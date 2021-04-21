package com.app.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
  private String path;
  private final File file;
  private Scanner reader;

  FileHandler(String path) {
    try {
      setPath(path);
    } catch (FileNotFoundException e) {
      // TODO
    }
    file = new File(this.path);
    createFileOnPath();
  }

  public void setPath(String path) throws FileNotFoundException {
    if (!file.isFile()) {
      throw new FileNotFoundException("File not found");
    }
    this.path = path;
  }

  ArrayList<String> readFile() { //TODO Refactor try/catch to be caught in controller instead of here
    ArrayList<String> result = new ArrayList();
    try {
      reader = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("couldn't find file at: " + path); //TODO REFACTOR
    }
    {
      while (reader.hasNextLine()) {
        result.add(reader.nextLine());
      }
      reader.close();
    }
    return result;
  }

  void writeFile(String[] input) {
    try {
      PrintStream printStream = new PrintStream(file);
      for (String s : input) {
        printStream.println(s);
      }
      printStream.close();

    } catch (FileNotFoundException e) {
      System.out.println("couldn't find file at: " + path); //TODO Refactor
    }
  }

  private void createFileOnPath() {
    if (!file.exists()) {
      String[] subPaths = path.split("/");
      StringBuilder dirPath = new StringBuilder();
      for (int i = 0; i < subPaths.length - 1; i++) {
        dirPath.append(subPaths[i]).append("/");
      }
      File dirs = new File(dirPath.toString());
      dirs.mkdirs();
      try {
        file.createNewFile();
      } catch (IOException e) {
        System.out.println("Invalid path"); //TODO Refactor
      }
    }
  }
}
