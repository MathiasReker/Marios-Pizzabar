package com.app.controllers;

import com.app.models.*;
import com.app.models.services.OrderService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
  private final OrderView ORDER_VIEW = new OrderView();
  private final ValidatorModel validator = new ValidatorModel();
  private final ItemController ITEM_CONTROLLER = new ItemController();
  private Scanner scanner = new Scanner(System.in);
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

  public OrderController(Scanner input) {
    this.scanner = input; // TODO the constructor is never used.
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
    String id = scanner.nextLine();

    ORDER_VIEW.printInline("How many items would you like to add: ");
    int qty = validator.validInputInt();
    return new OrderLineModel(qty, ITEM_CONTROLLER.lookupItem(id));
  }

  public void createOrder() {
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();

    boolean keepRunning = true;
    String userInput;

    try {
      orderLineModels.add(createOrderLine());
    } catch (IllegalArgumentException e) {
      ORDER_VIEW.printInline("Not a valid ID, please try again.");
    }
    // while not Q true keepRunning

    while (keepRunning) {
      ORDER_VIEW.printInline("Add another line to your order (Y/N): ");
      userInput = scanner.nextLine().toUpperCase();

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
    orderService.saveOrdersToFile(orderModels);
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
    viewActiveOrders();

    ORDER_VIEW.printInline("Order to complete: ");
    int orderId = validateInteger(scanner);

    OrderModel order = lookupOrder(orderId, orderModels);
    if (order != null) {
      order.setOrderStatus(status);
      ORDER_VIEW.printSuccess("Completed order #" + orderId);
      orderService.saveOrdersToFile(orderModels);
    } else {
      ORDER_VIEW.printWarning("Unable to find order " + orderId);
    }
  }

  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      ORDER_VIEW.printInline("Please input an integer: ");
      in.next();
    }

    return in.nextInt();
  }

  private OrderModel lookupOrder(int orderID, ArrayList<OrderModel> list) {
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
