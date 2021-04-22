package com.app.controllers;

import com.app.models.ITEM_MODEL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemControllerTest {
  public static ItemController testItemController;
  static int indexes;

  @BeforeAll
  public static void testSetup() {
    testItemController = new ItemController();
    indexes = testItemController.getItemModels().length;
    String input = "test\ntest\ntest\n1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    testItemController.createItem(new Scanner(System.in));
  }

  @Test
  public void testAppendItem() {
    ItemController testItemController = new ItemController();

    ITEM_MODEL testItem = new ITEM_MODEL("test", "Test", "test", 1);

    ITEM_MODEL[] testItems = testItemController.getItemModels();

    ITEM_MODEL[] actual = testItemController.appendItem(testItem);

    assertEquals(testItems.length + 1, actual.length);
  }

  @Test
  public void testDeleteItem() {
    ItemController testItemController = new ItemController();
    int expected = testItemController.getItemModels().length - 1;

    String input = String.valueOf(indexes);
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    testItemController.deleteItem(new Scanner(in));

    assertEquals(expected, testItemController.getItemModels().length);

  }
}
