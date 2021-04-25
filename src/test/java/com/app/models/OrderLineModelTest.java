package com.app.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineModelTest {

  @Test
  public void getValidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1500;
    int actual = orderLine.getSubTotal();

    assertEquals(expected, actual);
  }

  @Test
  public void getInValidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1400;
    int actual = orderLine.getSubTotal();

    assertNotEquals(expected, actual);
  }

  @Test
  public void getValidQuantity(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 3;
    int actual = orderLine.getQty();

    assertEquals(expected, actual);

  }

  @Test
  public void getInValidQuantity(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 2;
    int actual = orderLine.getQty();

    assertNotEquals(expected, actual);

  }

  @Test
  public void setInValidQty(){
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);

    assertThrows(IllegalArgumentException.class, () -> orderLine.setQty(-3));
  }

  @Test void getValidUnitPrice(){

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 3;
    int actual = orderLine.getQty();

    assertEquals(expected, actual);

  }

}