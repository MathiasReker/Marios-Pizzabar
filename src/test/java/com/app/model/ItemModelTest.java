package com.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemModelTest {

  @Test
  void getIdTest(){
    ItemModel test = new ItemModel("1", "test", "test", 100);


    Assertions.assertEquals("1", test.getId());

  }

}
