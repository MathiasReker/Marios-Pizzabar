package com.app.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineModelTest {

  @Test
  public void testGetValidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1500;
    int actual = orderLine.getSubTotal();

    assertEquals(expected, actual);
  }

  @Test
  public void testGetInValidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1400;
    int actual = orderLine.getSubTotal();

    assertNotEquals(expected, actual);
  }

  @Test
  public void testGetValidQuantity(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 3;
    int actual = orderLine.getQty();

    assertEquals(expected, actual);

  }

  @Test
  public void testGetInValidQuantity(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 2;
    int actual = orderLine.getQty();

    assertNotEquals(expected, actual);

  }

  @Test
  public void testSetInValidQty(){
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);

    assertThrows(IllegalArgumentException.class, () -> orderLine.setQty(-3));
  }

  @Test
  void testGetValidUnitPrice(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 500;
    int actual = orderLine.getUnitPrice();

    assertEquals(expected, actual);

  }
  @Test
  void testGetInValidUnitPrice(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 400;
    int actual = orderLine.getUnitPrice();

    assertNotEquals(expected, actual);

  }

}