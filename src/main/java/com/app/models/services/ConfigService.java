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
      String[] line = s.split(";");

      if (line[0].equals(KEY)) {
        return line[1];
      }
    }

    return null;
  }
}
