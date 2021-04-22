package com.app.models;

import com.app.models.services.ConfigService;
import com.app.models.services.FileService;
import com.app.models.services.OrderService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsModel {
  private final OrderService ORDER_PARSER_MODEL = new OrderService();
  private final ArrayList<OrderModel> orderModels = ORDER_PARSER_MODEL.getOrdersFromFile();

  public StatisticsModel() throws FileNotFoundException {
  }

  public int countOrdersToday() {
    return orderModels.size();
  }

  public double salesPerHour() {
    int openHours = 22 - 12;
    int result = 0;
    for (String s : listAllOrders()) {
      try {
        result += salePerDay("data/orderdb/" + s); // TODO
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    return (double) result / openHours / listAllOrders().size();
  }

  public ArrayList<String> listAllOrders() {
    try {
      String order = new ConfigService("orderDb").getPath();
      ArrayList<String> result = new ArrayList<>();
      File[] files = new File(order).listFiles();

      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            result.add(file.getName());
          }
        }
      }

      return result;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  public int salePerDay(String path) throws FileNotFoundException {
    FileService file = new FileService(path);
    ArrayList<String> fileInfo = file.readFile();

    return fileInfo != null ? fileInfo.size() : 0;
  }

  public int totalSalesPerDay() throws FileNotFoundException {
    int result = 0;
    for (String s : listAllOrders()) {
      result += salePerDay("data/orderdb/" + s);
    }

    return result / listAllOrders().size();
  }
}
