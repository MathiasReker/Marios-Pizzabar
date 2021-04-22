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
  private final Scanner scanner = new Scanner(System.in);
  private OrderService ORDER_PARSER_MODEL = null;
  private ArrayList<OrderModel> orderModels;

  {
    try {
      ORDER_PARSER_MODEL = new OrderService();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
  }

  {
    try {
      orderModels = ORDER_PARSER_MODEL.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
  }

  public OrderLineModel createOrderLine() {
    ORDER_VIEW.printTxt("How many items do you wish to add: ");
    int qty = scanner.nextInt();
    scanner.nextLine();
    ORDER_VIEW.printTxt("Please enter id: ");
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
          ORDER_VIEW.printTxt("Do you wish to add more to your order? Y/N"); // TODO: Display menu instead
          break;
        case "N":
          ORDER_VIEW.printTxt("Your order is completed.");
          keepRunning = false;
          break;
        default:
          ORDER_VIEW.printTxt("Not a valid input, please input Y for yes or N for no");
          break;
      }
    }

    orderModels.add(new OrderModel(generateOrderId(), 0, orderLineModels));
    ORDER_PARSER_MODEL.saveOrdersToFile(orderModels);
  }

  public ItemModel lookupItem(String itemId) {
    String path = null;
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
    final ItemService ITEM_PARSER = new ItemService(path);
    ItemModel[] itemModels;
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
      for (int i = 0; i < itemModels.length; i++) {
        if (itemId.equals(itemModels[i].getId())) {
          return itemModels[i];
        }
      }
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
    return null;
  }

  public void viewOrders() {
    for (OrderModel order : orderModels) {
      String[] formattedOrderlines = formatOrderLinesToStrings(order);
      ORDER_VIEW.printReceipt(
          order.getOrderId(), order.getTimeOfOrder(), formattedOrderlines, order.totalPrice());
    }
  }

  public String generateOrderId() {
    int highestNumber = orderModels.size();

    return "O" + (highestNumber + 1); // TODO: move to Model
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
  }

}
