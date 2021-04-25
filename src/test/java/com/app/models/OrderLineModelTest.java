package com.app.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderLineModelTest {

  @Test
  public void testGetValidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1500;
    int actual = orderLine.getSubTotal();

    assertEquals(expected, actual);
  }

  @Test
  public void testGetInvalidSubTotal() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1400;
    int actual = orderLine.getSubTotal();

    assertNotEquals(expected, actual);
  }

  @Test
  public void testGetValidQuantity() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 3;
    int actual = orderLine.getQty();

    assertEquals(expected, actual);

  }

  @Test
  public void testGetInvalidQuantity() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 2;
    int actual = orderLine.getQty();

    assertNotEquals(expected, actual);

  }

  @Test
  public void testSetValidQty() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);
    orderLine.setQty(3);

    int expected = 3;

    assertEquals(expected, orderLine.getQty());
  }

  @Test
  public void testSetInvalidQty() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);

    assertThrows(IllegalArgumentException.class, () -> orderLine.setQty(-3));
  }


  @Test
  public void testGetValidUnitPrice() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 500;
    int actual = orderLine.getUnitPrice();

    assertEquals(expected, actual);

  }

  @Test
  public void testGetInValidUnitPrice() {

    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    int expected = 400;
    int actual = orderLine.getUnitPrice();

    assertNotEquals(expected, actual);

  }

  @Test

  public void testGetValidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    ItemModel expectedItem = itemModel;

    assertEquals(expectedItem, orderLine.getItem());
  }

  @Test
  public void testGetInvalidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    ItemModel itemModel2 = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);
    OrderLineModel orderLine2 = new OrderLineModel(3, itemModel2);

    ItemModel expectedItem = itemModel;

    assertNotEquals(expectedItem, orderLine2.getItem());
  }

  @Test
  public void testSetInvalidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);

    OrderLineModel orderLine = new OrderLineModel(3, itemModel);


    assertThrows(IllegalArgumentException.class, () -> orderLine.setItem(null));

  }

  @Test
  public void testSetValidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    ItemModel itemModel2 = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    orderLine.setItem(itemModel2);

    assertEquals(itemModel2, orderLine.getItem());
  }

}