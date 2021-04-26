package com.app.controllers;

import com.app.models.ItemModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class ItemControllerTest {
  public static ItemController testItemController;

  @BeforeAll
  public static void testSetup() {
    testItemController = new ItemController();
    String input = "test\ntest\ntest\n1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    testItemController.createItem(new Scanner(System.in));
  }

  @Test
  public void testAppendItem() {
    ItemController testItemController = new ItemController();
    ItemModel testItem = new ItemModel("test", "Test", "test", 1);
    ItemModel[] testItems = testItemController.getItemModels();
    ItemModel[] actual = testItemController.appendItem(testItem);

    Assertions.assertEquals(testItems.length + 1, actual.length);
  }
}
