package com.app.controllers;

import com.app.models.ItemModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ItemControllerTest {
  public static ItemController testItemController;

  @Test
  public void testAppendItem() {
    ItemController testItemController = new ItemController();
    ItemModel testItem = new ItemModel("test", "Test", "test", 1);
    ItemModel[] testItems = testItemController.getItemModels();
    ItemModel[] actual = testItemController.appendItem(testItem);

    Assertions.assertEquals(testItems.length + 1, actual.length);
  }
}
