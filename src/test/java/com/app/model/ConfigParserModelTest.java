package com.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class ConfigParserModelTest {
  @Test
  public void testGetInvalidPath() {
    ConfigParserModel configParserModel = new ConfigParserModel("Test");
    String result;
    try {
      result = configParserModel.getPath();
    } catch (FileNotFoundException e) {
      result = null;
    }
    Assertions.assertEquals("", result);
  }

  @Test
  public void testGetValidPath() {
    ConfigParserModel configParserModel = new ConfigParserModel("itemDb");
    String result;
    try {
      result = configParserModel.getPath();
    } catch (FileNotFoundException e) {
      result = null;
    }
    Assertions.assertEquals("data/itemdb/items.txt", result);
  }
}