package com.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class FileHandlerTest {
  FileHandler fileHandler;

  @Test
  public void testReadValidFile() {
    FileHandler fileHandler = new FileHandler("src/test/java/test_file.txt");
    ArrayList<String> result = fileHandler.readFile();
    Assertions.assertEquals(new ArrayList<String>(Arrays.asList("foo;bar")), result);
  }

  @Test
  public void testReadInvalidFile() {
    Assertions.assertThrows(NullPointerException.class, () -> fileHandler = new FileHandler("foo/bar/baz.txt"));
   }

  @Test
  public void testWriteFile() {
    fileHandler.writeFile(new String[]{"input"});
  }
}