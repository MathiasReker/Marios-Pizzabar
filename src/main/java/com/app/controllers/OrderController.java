package com.app.controllers;

import com.app.models.*;
import com.app.models.services.OrderService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
  private final OrderView ORDER_VIEW = new OrderView();
  private final ValidatorModel VALIDATOR = new ValidatorModel();
  private final ItemController ITEM_CONTROLLER = new ItemController();
  private final Scanner SCANNER = new Scanner(System.in);
  private OrderService orderService;
  private ArrayList<OrderModel> orderModels;

  public OrderController() {

    try {
      orderService = new OrderService();
      orderModels = orderService.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
  }

  public OrderLineModel createOrderLine() {
    ItemModel[] readItems = ITEM_CONTROLLER.getItemModels();
    String[] result = new String[readItems.length];

    String[] itemId = new String[result.length];
    String[] itemName = new String[result.length];
    int[] unitPrice = new int[result.length];

    for (int i = 0; i < result.length; i++) {
      itemId[i] = readItems[i].getId();
      itemName[i] = readItems[i].getItemName();
      unitPrice[i] = readItems[i].getPrice();
    }

    ORDER_VIEW.printMenuOptions("Id", "Item", "Price", itemId, itemName, unitPrice);
    ORDER_VIEW.printInline("Enter the ID of the item: ");
    String id = SCANNER.nextLine(); // TODO: VALIDATE

    ORDER_VIEW.printInline("How many items would you like to add: ");
    int qty = VALIDATOR.validInputInt(); // TODO: VALIDATE
    return new OrderLineModel(qty, ITEM_CONTROLLER.lookupItem(id));
  }


  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      ORDER_VIEW.printInlineWarning("Not a valid menu choice. Please try again: ");
      in.nextLine(); // TODO: VALIDATE
    }

    return in.nextInt();
  }

  public void createOrder() {
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();

    boolean keepRunning = true;
    String userInput;

    try {
      orderLineModels.add(createOrderLine());
    } catch (IllegalArgumentException e) {
      ORDER_VIEW.printInline("Not a valid ID, please try again."); // TODO: VALIDATE
    }

    while (keepRunning) {
      ORDER_VIEW.printInline("Add another line to your order (Y/N): ");
      userInput = SCANNER.nextLine().toUpperCase(); // TODO: VALIDATE

      switch (userInput) {
        case "Y":
          try {
            orderLineModels.add(createOrderLine());
          } catch (IllegalArgumentException e) {
            ORDER_VIEW.printInlineWarning("Not a valid input.");
          }
          break;
        case "N":
          ORDER_VIEW.printSuccess("Your order is registered.");
          keepRunning = false;
          break;
        default:
          ORDER_VIEW.printInlineWarning("Invalid input. Try again (Y/N): ");
          break;
      }
    }

    orderModels.add(new OrderModel(generateOrderId(), OrderStatusKeys.ACTIVE, orderLineModels));
    try {
      orderService.saveOrdersToFile(orderModels);
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printWarning("The files does not exist.");
    }
  }

  public void viewActiveOrders() {
    for (OrderModel order : orderModels) {
      String[] formattedOrderLines = formatOrderLinesToStrings(order);
      if (order.getOrderStatus() == OrderStatusKeys.ACTIVE) {
        ORDER_VIEW.printReceipt(
            order.getOrderId(), order.getTimeOfOrder(), formattedOrderLines, order.totalPrice());
      }
    }
  }

  public int generateOrderId() {
    return orderModels.size() + 1;
  }

  public void changeOrderStatus(OrderStatusKeys status) {
    if (0 == orderModels.size()) {
      ORDER_VIEW.printWarning("No orders available.");
    } else {
      ORDER_VIEW.printInline("Order to complete: ");
      int orderId = validateInteger(SCANNER);

      OrderModel order = lookupOrder(orderId, orderModels);
      if (order != null) {
        order.setOrderStatus(status);
        ORDER_VIEW.printSuccess("Completed order #" + orderId + ".");
        try {
          orderService.saveOrdersToFile(orderModels);
        } catch (FileNotFoundException e) {
          ORDER_VIEW.printWarning("The files does not exist.");
        }
      } else {
        ORDER_VIEW.printWarning("Unable to find order #" + orderId + ".");
      }
    }
  }

  OrderModel lookupOrder(int orderID, ArrayList<OrderModel> list) {
    for (OrderModel order : list) {
      if (order.getOrderId() == orderID) {
        return order;
      }
    }

    return null;
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
}
