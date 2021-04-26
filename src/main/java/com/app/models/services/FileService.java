package com.app.models.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {
  private final File FILE;
  private final String PATH;

  public FileService(String path) throws IOException {
    PATH = path;
    FILE = new File(PATH);
    createFileOnPath();
  }

  public ArrayList<String> readFile() throws FileNotFoundException {
    ArrayList<String> result = new ArrayList();

    Scanner reader = new Scanner(FILE);

    while (reader.hasNextLine()) {
      result.add(reader.nextLine());
    }
    reader.close();

    return result;
  }

  public void writeFile(String[] input) throws FileNotFoundException {
    PrintStream printStream = new PrintStream(FILE);
    for (String s : input) {
      printStream.println(s);
    }
    printStream.close();
  }

  private void createFileOnPath() throws IOException {
    if (!FILE.exists()) {
      String[] subPaths = PATH.split("/");
      StringBuilder dirPath = new StringBuilder();
      for (int i = 0; i < subPaths.length - 1; i++) {
        dirPath.append(subPaths[i]).append("/");
      }
      File dirs = new File(dirPath.toString());
      dirs.mkdirs();
      FILE.createNewFile();
    }
  }
}
