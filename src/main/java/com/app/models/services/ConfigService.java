package com.app.models.services;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ConfigService {
  private final String KEY;

  public ConfigService(String key) {
    this.KEY = key;
  }

  public String getPath() throws FileNotFoundException {
    FileService file = new FileService("data/config.txt");
    ArrayList<String> fileInfo = file.readFile();

    for (String s : fileInfo) {
      String[] arr = s.split(";");

      if (arr[0].equals(KEY)) {
        return arr[1];
      }
    }

    return null;
  }
}
