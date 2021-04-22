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
  public void testGetPrice() {
    ItemModel test = new ItemModel("1", "test", "test", 100);

    Assertions.assertEquals(100, test.getPrice());
  }


  @Test
  public void testAdd() {
    Assertions.assertEquals(1, 1);
  }

}
