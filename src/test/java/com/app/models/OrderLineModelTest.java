package com.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderLineModelTest {

  @Test
  public void testGetValidSubTotal() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1500;
    int actual = orderLine.getSubTotal();

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetInvalidSubTotal() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 1400;
    int actual = orderLine.getSubTotal();

    Assertions.assertNotEquals(expected, actual);
  }

  @Test
  public void testGetValidQuantity() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 3;
    int actual = orderLine.getQty();

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetInvalidQuantity() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 2;
    int actual = orderLine.getQty();

    Assertions.assertNotEquals(expected, actual);
  }

  @Test
  public void testSetValidQty() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);
    orderLine.setQty(3);

    int expected = 3;

    Assertions.assertEquals(expected, orderLine.getQty());
  }

  @Test
  public void testSetInvalidQty() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(1, itemModel);

    Assertions.assertThrows(IllegalArgumentException.class, () -> orderLine.setQty(-3));
  }


  @Test
  public void testGetValidUnitPrice() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 500;
    int actual = orderLine.getUnitPrice();

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetInValidUnitPrice() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    int expected = 400;
    int actual = orderLine.getUnitPrice();

    Assertions.assertNotEquals(expected, actual);
  }

  @Test
  public void testGetValidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    Assertions.assertEquals(itemModel, orderLine.getItem());
  }

  @Test
  public void testGetInvalidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    ItemModel itemModel2 = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine2 = new OrderLineModel(3, itemModel2);

    Assertions.assertNotEquals(itemModel, orderLine2.getItem());
  }

  @Test
  public void testSetInvalidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    Assertions.assertThrows(IllegalArgumentException.class, () -> orderLine.setItem(null));
  }

  @Test
  public void testSetValidItem() {
    ItemModel itemModel = new ItemModel("1", "pizza", "tomat ost", 500);
    ItemModel itemModel2 = new ItemModel("1", "pizza", "tomat ost", 500);
    OrderLineModel orderLine = new OrderLineModel(3, itemModel);

    orderLine.setItem(itemModel2);

    Assertions.assertEquals(itemModel2, orderLine.getItem());
  }
}
