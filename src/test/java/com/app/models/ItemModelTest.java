package com.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemModelTest {
  @Test
  public void testGetId() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertEquals("1", test.getId());
  }

  @Test
  public void testInvalidGetId() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertNotEquals("2", test.getId());
  }

  @Test
  public void testGetPrice() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertEquals(100, test.getPrice());
  }

  @Test
  public void testInvalidGetPrice() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertNotEquals(200, test.getPrice());
  }


  @Test
  public void testValidGetName() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertEquals("test", test.getItemName());
  }

  @Test
  public void testInvalidGetname() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertNotEquals("invalidname", test.getItemName());
  }

  @Test
  public void testValidGetItemDescription() {
    ItemModel test = new ItemModel("1", "test", "description", 100);

    Assertions.assertEquals("description", test.getItemDescription());
  }

  @Test
  public void testInvalidGetItemDescription() {
    ItemModel test = new ItemModel("1", "test", "description", 100);

    Assertions.assertNotEquals("invalid description", test.getItemDescription());
  }
}
