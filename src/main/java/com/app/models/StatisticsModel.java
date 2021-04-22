package com.app.models;

import com.app.models.services.ConfigService;
import com.app.models.services.FileService;
import com.app.models.services.OrderService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsModel {
  private final OrderService ORDER_SERVICE = new OrderService();
  private final ArrayList<OrderModel> orderModels = ORDER_SERVICE.getOrdersFromFile();

  private final String ORDER_DB = new ConfigService("orderDb").getPath();

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
        result += salePerDay(ORDER_DB + s); // TODO
      } catch (FileNotFoundException e) {
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

  public int salePerDay(String path) throws FileNotFoundException {
    FileService file = new FileService(path);
    ArrayList<String> fileInfo = file.readFile();

    return fileInfo != null ? fileInfo.size() : 0;
  }

  public int totalSalesPerDay() throws FileNotFoundException {
    int result = 0;
    for (String s : listAllOrders()) {
      result += salePerDay(ORDER_DB + s); // TODO
    }

    return result / listAllOrders().size();
  }
}
