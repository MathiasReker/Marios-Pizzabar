package com.app.models.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ConfigServiceTest {

  @Test
  public void testValidGetPath() {
    ConfigService configService = new ConfigService("itemDb");
    String result = null;
    try {
      result = configService.getPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assertions.assertEquals("data/itemdb/items.txt", result);
  }

  @Test
  public void testInvalidGetPath1() {
    ConfigService configService = new ConfigService("test");
    String result = null;
    try {
      result = configService.getPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }

  @Test
  public void testInvalidGetPath2() {
    ConfigService configService = new ConfigService("");
    String result = null;
    try {
      result = configService.getPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }

  @Test
  public void testInvalidGetPath3() {
    ConfigService configService = new ConfigService(null);
    String result = null;
    try {
      result = configService.getPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assertions.assertNull(result);
  }
}
