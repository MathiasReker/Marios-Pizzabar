package com.app.models;

import com.app.controllers.MenuController;
import com.app.controllers.menuactions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuModelTest {

  @Test
  public void testValidGetLeadText() {
    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new OrderViewSubmenuMenuAction("Order view"),
        new CancelOrderMenuAction("Cancel order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit")};

    MenuModel menuModel = new MenuModel("Menu header", menu, "Leadtext");


    assertEquals("Leadtext", menuModel.getLeadText());
  }

  @Test
  public void testInvalidGetLeadText() {
    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new OrderViewSubmenuMenuAction("Order view"),
        new CancelOrderMenuAction("Cancel order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit")};

    MenuModel menuModel = new MenuModel("Menu header", menu, "Leadtext");

    assertNotEquals("Wrong text", menuModel.getLeadText());
  }

  @Test
  public void testValidGetMenuHeader() {
    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new OrderViewSubmenuMenuAction("Order view"),
        new CancelOrderMenuAction("Cancel order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit")};

    MenuModel menuModel = new MenuModel("Menu header", menu, "Leadtext");

    assertEquals("Menu header", menuModel.getMenuHeader());
  }

  @Test
  public void testInvalidGetMenuHeader() {
    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new OrderViewSubmenuMenuAction("Order view"),
        new CancelOrderMenuAction("Cancel order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit")};

    MenuModel menuModel = new MenuModel("Menu header", menu, "Leadtext");

    assertNotEquals("wrong text", menuModel.getMenuHeader());
  }
}