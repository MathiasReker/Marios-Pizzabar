package com.app.models.services;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ConfigService {
  String key;

  public ConfigService(String key) {
    setKey(key);
  }

  public void setKey(String key) {
    // Validate
    this.key = key;
  }

  public String getPath() throws FileNotFoundException {
    FileService file = new FileService("data/config.txt");
    ArrayList<String> fileInfo = file.readFile();

    String result = "";

    for (String s : fileInfo) {
      String[] arr = s.split(";");

      if (arr[0].equals(key)) {
        result = arr[1];
      }
    }

    return result;
  }
}