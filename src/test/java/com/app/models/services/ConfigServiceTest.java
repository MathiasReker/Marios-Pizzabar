package com.app.models.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class ConfigServiceTest {
  
  @Test
  void testValidGetPath() {
    ConfigService configService = new ConfigService("itemDb");
    String result = null;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Assertions.assertEquals("data/itemdb/items.txt", result);
  }

  @Test
  void testInvalidGetPath1() {
    ConfigService configService = new ConfigService("test");
    String result = null;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }

  @Test
  void testInvalidGetPath2() {
    ConfigService configService = new ConfigService("");
    String result = null;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }

  @Test
  void testInvalidGetPath3() {
    ConfigService configService = new ConfigService(null);
    String result = null;
    try {
      result = configService.getPath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }
}
