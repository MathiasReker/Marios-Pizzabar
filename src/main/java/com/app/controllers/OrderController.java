package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.models.services.OrderService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class OrderController {

  private final OrderView ORDER_VIEW = new OrderView();
  private Scanner scanner = new Scanner(System.in);
  private OrderService orderService;
  private ArrayList<OrderModel> orderModels;

  {
    try {
      orderService = new OrderService();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
  }

  {
    try {
      orderModels = orderService.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
  }

  public OrderController(){

  };

  public OrderController(Scanner input){
    this.scanner = input;
  }

  public OrderLineModel createOrderLine() {
    ORDER_VIEW.printInline("How many items would you like to add: ");
    int qty = scanner.nextInt();
    scanner.nextLine();
    ORDER_VIEW.printInline("Please enter an ID: ");
    String id = scanner.nextLine();

    return new OrderLineModel(qty, lookupItem(id));
  }

  public void createOrder() {
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();

    boolean keepRunning = true;
    String userInput;

    orderLineModels.add(createOrderLine());

    while (keepRunning) {
      userInput = scanner.next().toUpperCase(Locale.ROOT);

      switch (userInput) {
        case "Y":
          orderLineModels.add(createOrderLine());
          ORDER_VIEW.printInline(
              "Do you wish to add more to your order? Y/N"); // TODO: Display menu instead: 1) Yes.
          // 2) No.
          break;
        case "N":
          ORDER_VIEW.printInline("Your order is completed.");
          keepRunning = false;
          break;
        default:
          ORDER_VIEW.printInline("Not a valid input, please input Y for yes or N for no");
          break;
      }
    }

    orderModels.add(new OrderModel(generateOrderId(), 0, orderLineModels));
    orderService.saveOrdersToFile(orderModels);
  }

  public ItemModel lookupItem(String itemId) {
    String path = null;
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
    final ItemService ITEM_PARSER = new ItemService(path);
    ItemModel[] itemModels;
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
      for (ItemModel itemModel : itemModels) {
        if (itemId.equals(itemModel.getId())) {
          return itemModel;
        }
      }
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }

    return null;
  }

  public void viewActiveOrders() {
    for (OrderModel order : orderModels) {
      String[] formattedOrderLines = formatOrderLinesToStrings(order);
      ORDER_VIEW.print("==== ACTIVE ORDER ====");
      if (order.getOrderStatus() == 0) {
        ORDER_VIEW.printReceipt(
            order.getOrderId(), order.getTimeOfOrder(), formattedOrderLines, order.totalPrice());
      }
      ORDER_VIEW.print("==== END ====");
    }
  }

  public String generateOrderId() {
    int highestNumber = orderModels.size();

    return "O"
        + (highestNumber
            + 1); // TODO: move to Model? Handle in file: Move generateOrderId() to Model #59
  }

  public void changeOrderStatus(int status) {
    ORDER_VIEW.printInline("Order to complete:");
    String orderId = scanner.nextLine();

    OrderModel order = lookupOrder(orderId, orderModels);
    if (order != null) {
      order.setOrderStatus(status);
      ORDER_VIEW.print("Completed order " + orderId);
      orderService.saveOrdersToFile(orderModels);
    } else {
      ORDER_VIEW.print("Could not find order " + orderId);
    }
  }

  OrderModel lookupOrder(String orderID, ArrayList<OrderModel> list) {
    for (OrderModel order : list) {
      if (order.getOrderId().equals(orderID)) {
        return order;
      }
    }
    return null;
  }

  private String[] formatOrderLinesToStrings(OrderModel order) {
    ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();
    String[] stringsResult = new String[orderLineModels.size()];

    for (int i = 0; i < orderLineModels.size(); i++) {
      stringsResult[i] =
          String.join(
              ";",
              orderLineModels.get(i).getItem().getId(),
              orderLineModels.get(i).getItem().getItemName(),
              String.valueOf(orderLineModels.get(i).getQty()),
              String.valueOf(orderLineModels.get(i).getUnitPrice()),
              String.valueOf(orderLineModels.get(i).getSubTotal()));
    }

    return stringsResult;
  } // WIP
}
