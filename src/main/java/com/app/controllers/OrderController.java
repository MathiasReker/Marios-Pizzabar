package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.OrderStatusKeys;
import com.app.models.services.OrderService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
  private final OrderView ORDER_VIEW = new OrderView();
  private final ItemController ITEM_CONTROLLER = new ItemController();
  private final Scanner SCANNER = new Scanner(System.in);
  private OrderService orderService;
  private ArrayList<OrderModel> orderModels;

  public OrderController() {
    try {
      orderService = new OrderService();
      orderModels = orderService.getOrdersFromFile();
    } catch (IOException e) {
      ORDER_VIEW.printInlineWarning(e.getMessage());
    }
  }

  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      ORDER_VIEW.printInlineWarning("Not a valid menu choice. Please try again: ");
      in.nextLine();
    }

    return in.nextInt();
  }

  public void createOrder() {
    orderModels.add(new OrderModel(orderModels.size() + 1, OrderStatusKeys.ACTIVE));

    boolean keepRunning = true;
    String userInput;

    try {
      ITEM_CONTROLLER.printItemMenu();
      orderModels.get(orderModels.size() - 1).addOrderLine(userInputGetItemID(), userInputGetQty());
    } catch (IllegalArgumentException e) {
      ORDER_VIEW.printInline("Not a valid ID, please try again.");
    }

    while (keepRunning) {

      ORDER_VIEW.printInline("Add another line to your order (Y/N): ");
      userInput = SCANNER.nextLine().toUpperCase();

      switch (userInput) {
        case "Y":
          try {
            orderModels
                .get(orderModels.size() - 1)
                .addOrderLine(userInputGetItemID(), userInputGetQty());
          } catch (IllegalArgumentException e) {
            ORDER_VIEW.printInlineWarning("Not a valid input.");
          }
          break;
        case "N":
          ORDER_VIEW.printSuccess("Your order is registered.");
          keepRunning = false;
          break;
        default:
          ORDER_VIEW.printWarning("Invalid input.");
          break;
      }
    }

    if (orderModels.get(orderModels.size() - 1).getOrderLines().size() == 0) {
      orderModels.remove(orderModels.size() - 1);
    } else {
      try {
        orderService.saveOrdersToFile(orderModels);
      } catch (FileNotFoundException e) {
        ORDER_VIEW.printWarning(e.getMessage());
      }
    }
  }

  public void viewActiveOrders() {
    if (hasActiveOrder()) {
      for (OrderModel order : orderModels) {
        String[] formattedOrderLines = formatOrderLinesToStrings(order);
        if (order.getOrderStatus() == OrderStatusKeys.ACTIVE) {
          ORDER_VIEW.printReceipt(
              order.getOrderId(),
              order.getTimeOfOrder(),
              formattedOrderLines,
              order.totalPrice(),
              order.getExpectedPickUpTime());
        }
      }
    } else {
      ORDER_VIEW.printWarning("0 active orders.");
    }
  }

  public void changeOrderStatus(OrderStatusKeys status) {
    if (0 == orderModels.size()) {
      ORDER_VIEW.printWarning("No orders available.");
    } else {
      if (hasActiveOrder()) {
        ORDER_VIEW.printInline("Order number: ");
        int orderId = validateInteger(SCANNER);

        OrderModel order = lookupOrder(orderId, orderModels);
        if (order != null) {
          if (order.getOrderStatus() == OrderStatusKeys.ACTIVE) {
            order.setOrderStatus(status);
            ORDER_VIEW.printSuccess("Order #" + orderId + " changed status to: " + status + ".");
            try {
              orderService.saveOrdersToFile(orderModels);
            } catch (FileNotFoundException e) {
              ORDER_VIEW.printWarning("The files does not exist.");
            }
          } else {
            ORDER_VIEW.printWarning(
                "Order #" + orderId + " is not " + OrderStatusKeys.ACTIVE + ".");
          }
        } else {
          ORDER_VIEW.printWarning("Order doesnt exist.");
        }
      } else {
        ORDER_VIEW.printWarning("No orders are changeable.");
      }
    }
  }

  OrderModel lookupOrder(int orderID, ArrayList<OrderModel> list) {
    for (OrderModel order : list) {
      if (order.getOrderId() == orderID) {
        return order;
      }
    }

    throw new IllegalArgumentException();
  }

  private String[] formatOrderLinesToStrings(OrderModel order) {
    ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();
    String[] result = new String[orderLineModels.size()];

    for (int i = 0; i < orderLineModels.size(); i++) {
      result[i] =
          String.join(
              ";",
              orderLineModels.get(i).getItem().getId(),
              orderLineModels.get(i).getItem().getItemName(),
              String.valueOf(orderLineModels.get(i).getQty()),
              String.valueOf(orderLineModels.get(i).getUnitPrice()),
              String.valueOf(orderLineModels.get(i).getSubTotal()));
    }

    return result;
  }

  public ArrayList<OrderModel> getOrderModels() {
    return orderModels;
  }

  public ItemModel userInputGetItemID() {
    boolean itemExists = false;
    ItemModel item = null;
    while (!itemExists) {
      ORDER_VIEW.printInline("Enter the ID of the item: ");
      String id = SCANNER.nextLine();
      item = ITEM_CONTROLLER.lookupItem(id);
      if (item != null) {
        itemExists = true;
      } else {
        ORDER_VIEW.printWarning("Item doesnt exist, try again!");
      }
    }
    return item;
  }

  public int userInputGetQty() {
    ORDER_VIEW.printInline("How many items would you like to add: ");
    while (!SCANNER.hasNextInt()) {
      ORDER_VIEW.printInlineWarning("Not a valid quantity");
      SCANNER.nextLine();
    }
    int result = SCANNER.nextInt();
    SCANNER.nextLine();
    return result;
  }

  private boolean hasActiveOrder() {
    for (OrderModel order : orderModels) {
      if (order.getOrderStatus() == OrderStatusKeys.ACTIVE) {
        return true;
      }
    }
    return false;
  }
}
