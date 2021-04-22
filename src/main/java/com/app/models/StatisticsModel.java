package com.app.models;

import com.app.models.services.ConfigService;
import com.app.models.services.FileService;
import com.app.models.services.OrderService;
import com.app.views.StatisticsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsModel {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private OrderService ORDER_PARSER_MODEL = null;
  private ArrayList<OrderModel> orderModels;

  {
    try {
      ORDER_PARSER_MODEL = new OrderService();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print("File does not exists.");
    }
  }

  {
    try {
      orderModels = ORDER_PARSER_MODEL.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print("File does not exists.");
    }
  }

  public int countOrdersToday() {
    return orderModels.size();
  }

  public double salesPerHour() {
    int openHours = 22 - 12;
    int result = 0;
    for (String s : listAllOrders()) {
      result += salePerDay("data/orderdb/" + s);
    }
    return (double) result / openHours / listAllOrders().size();

  }

  public ArrayList<String> listAllOrders() {
    String order = null;
    try {
      order = new ConfigService("orderDb").getPath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // TODO: Test if no files
    ArrayList<String> result = new ArrayList<>();

    File[] files = new File(order).listFiles();

    for (File file : files) {
      if (file.isFile()) {
        result.add(file.getName());
      }
    }

    return result;
  }

  public int salePerDay(String path) {
    FileService file = new FileService(path);
    ArrayList<String> fileInfo = null;
    try {
      fileInfo = file.readFile();
    } catch (FileNotFoundException e) {
      // TODO
    }

    return fileInfo.size();
  }

  public int totalSalesPerDay() {
    int result = 0;
    for (String s : listAllOrders()) {
      result += salePerDay("data/orderdb/" + s);
    }

    return result / listAllOrders().size();
  }
}
