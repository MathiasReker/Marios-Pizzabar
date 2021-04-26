package com.app.models;

import com.app.controllers.ItemController;
import com.app.controllers.OrderController;
import com.app.models.services.ConfigService;
import com.app.models.services.FileService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StatisticsModel {
  private final String ORDER_DB = new ConfigService("orderDb").getPath();
  private final OrderController orderController = new OrderController();
  private final ArrayList<OrderModel> orderModels = orderController.getOrderModels();

  public StatisticsModel() throws IOException {
  }

  public int countOrdersToday() {
    return orderModels.size();
  }

  public double salesPerHour() {
    int openHours = 22 - 12;
    int result = 0;
    for (String s : listAllOrders()) {
      try {
        result += salePerDay(ORDER_DB + s);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return (double) result / openHours / listAllOrders().size();
  }

  public ArrayList<String> listAllOrders() {
    ArrayList<String> result = new ArrayList<>();
    File[] files = new File(ORDER_DB).listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          result.add(file.getName());
        }
      }
    }

    return result;
  }

  public int salePerDay(String path) throws IOException {
    FileService file = new FileService(path);
    ArrayList<String> fileInfo = file.readFile();

    return fileInfo != null ? fileInfo.size() : 0;
  }

  public int totalSalesPerDay() throws IOException {
    int result = 0;
    for (String s : listAllOrders()) {
      result += salePerDay(ORDER_DB + s);
    }

    return result / listAllOrders().size();
  }

  public String[] menuItems() {
    ItemController itemController = new ItemController();
    ItemModel[] itemModels = itemController.getItemModels();
    String[] result = new String[itemModels.length];

    for (int i = 0; i < itemModels.length; i++) {
      result[i] = itemModels[i].getItemName();
    }

    return result;
  }


  public int[] salesPerItemPerDay() {
    String[] menuItems = menuItems();
    int[] result = new int[menuItems.length];
    for (int i = 0; i < menuItems.length; i++) {
      for (OrderModel order : orderModels) {
        if (order.getOrderStatus().equals(OrderStatusKeys.COMPLETE)) {
          ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();
          for (OrderLineModel orderLineModel : orderLineModels) {
            String pizzaName = orderLineModel.getItem().getItemName();
            int qty = orderLineModel.getQty();
            if (pizzaName.equals(menuItems[i])) {
              result[i] += qty;
            }
          }
        }
      }
    }

    return result;
  }
}
