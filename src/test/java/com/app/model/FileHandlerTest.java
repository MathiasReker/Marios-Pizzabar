package com.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class FileHandlerTest {
  @Test
  public void testReadValidFile() {
    FileHandler fileHandler = new FileHandler("src/test/java/test_file.txt");
    ArrayList<String> result = fileHandler.readFile();
    Assertions.assertEquals(new ArrayList<String>(Arrays.asList("foo;bar")), result);
  }
}