package com.app.controllers;

import com.app.controllers.menuactions.OrderSubmenuMenuAction;
import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.ValidatorModel;
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
  private final Scanner SCANNER = new Scanner(System.in);
  private final ValidatorModel validator = new ValidatorModel();
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

  public OrderLineModel createOrderLine() {
    ORDER_VIEW.printInline("How many items would you like to add: ");
    int qty = validator.validInputInt(); //TODO validate
    SCANNER.nextLine();
    ORDER_VIEW.printInline("Please enter an ID: ");
    String id = validator.getValidId(SCANNER.nextLine()); //TODO validate
    ORDER_VIEW.printInline("Do you wish to add more to your order? Y/N");

    return new OrderLineModel(qty, lookupItem(id));
  }

  public void createOrder() {
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    OrderSubmenuMenuAction orderSubmenuMenuAction = new OrderSubmenuMenuAction("Ordermenu");

    boolean keepRunning = true;
    String userInput;


    orderLineModels.add(createOrderLine());
    orderSubmenuMenuAction.run();



 /*   while (keepRunning) {
      userInput = SCANNER.next().toUpperCase(Locale.ROOT);

      switch (userInput) {
        case "Y":
          orderLineModels.add(createOrderLine());
            // TODO: Display menu instead: 1) Yes. 2) No.
          break;
        case "N":
          ORDER_VIEW.printInline("Your order is completed.");
          keepRunning = false;
          break;
        default:
          ORDER_VIEW.printInline("Not a valid input, please input Y for yes or N for no");
          break;
      }
    }*/

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

  public void viewOrders() {
    for (OrderModel order : orderModels) {
      String[] formattedOrderLines = formatOrderLinesToStrings(order);
      ORDER_VIEW.printReceipt(
          order.getOrderId(), order.getTimeOfOrder(), formattedOrderLines, order.totalPrice());
    }
  }

  public String generateOrderId() {
    int highestNumber = orderModels.size();

    return "O" + (highestNumber + 1); // TODO: move to Model? Handle in file: Move generateOrderId() to Model #59
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
