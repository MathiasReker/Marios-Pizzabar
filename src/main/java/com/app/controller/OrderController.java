package com.app.controller;

import com.app.model.*;
import com.app.model.services.ConfigService;
import com.app.model.services.ItemService;
import com.app.model.services.OrderService;
import com.app.view.OrderView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class OrderController {

  private final OrderView ORDER_VIEW = new OrderView();
  private OrderService ORDER_PARSER_MODEL = null;

  {
    try {
      ORDER_PARSER_MODEL = new OrderService();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
  }

  private ArrayList<OrderModel> orderModels;

  {
    try {
      orderModels = ORDER_PARSER_MODEL.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
  }


  private final Scanner scanner = new Scanner(System.in);


  public OrderLineModel createOrderLine() {
    ORDER_VIEW.printTxt("How many items do you wish to add: ");
    int qty = scanner.nextInt();
    scanner.nextLine();
    ORDER_VIEW.printTxt("Please enter id: ");
    String id = scanner.nextLine();


    return new OrderLineModel(qty, item(id));
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
          ORDER_VIEW.printTxt("Do you wish to add more to your order? Y/N");
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

  public ItemModel item(String userId){
    String path = null;
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }
    final ItemService ITEM_PARSER = new ItemService(path);
    ItemModel[] itemModels = new ItemModel[0];
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printTxt("File does not exists.");
    }

    for (int i = 0; i < itemModels.length; i++) {
      if (userId.equals(itemModels[i].getId())){
        return itemModels[i];
      }
    }

    return null;
  }

  public void viewOrders(){
    for (OrderModel order: orderModels
         ) {
      System.out.println(order.getOrderLines().get(1));
      System.out.println(order.getTimeOfOrder());
      System.out.println(order.totalPrice());

    }
  }

  public String generateOrderId(){
    int highestNumber = orderModels.size();

    return "O" + (highestNumber+1);

  }
  // Create new
}
