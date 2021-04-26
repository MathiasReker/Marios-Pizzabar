package com.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AppTest {
  @Test
  public void testExit() {
    String input = "6";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    new App().run();
  }
}
