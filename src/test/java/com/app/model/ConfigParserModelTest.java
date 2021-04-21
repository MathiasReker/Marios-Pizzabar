package com.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfigParserModelTest {
  @Test
  void testGetInvalidPath() {
    ConfigParserModel configParserModel = new ConfigParserModel("Test");
    String result = configParserModel.getPath();
    Assertions.assertEquals("", result);
  }

  @Test
  void testGetValidPath() {
    ConfigParserModel configParserModel = new ConfigParserModel("itemDb");
    String result = configParserModel.getPath();
    Assertions.assertEquals("data/itemdb/items.txt", result);
  }
}
