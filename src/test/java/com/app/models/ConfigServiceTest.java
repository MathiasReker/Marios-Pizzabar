package com.app.models;

import com.app.models.services.ConfigService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class ConfigServiceTest {
  @Test
  public void testGetInvalidPath() {
    ConfigService configService = new ConfigService("Test");
    String result;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      result = null;
    }
    Assertions.assertEquals("", result);
  }

  @Test
  public void testGetValidPath() {
    ConfigService configService = new ConfigService("itemDb");
    String result;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      result = null;
    }
    Assertions.assertEquals("data/itemdb/items.txt", result);
  }
}