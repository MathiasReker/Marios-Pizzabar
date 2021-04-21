package com.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderParserModel {

  private final FileHandler ORDER_FILE;

  public OrderParserModel() {
    String PATH = new ConfigParserModel("orderDb").getPath();
    String FILENAME = LocalDate.now() + ".txt";
    ORDER_FILE = new FileHandler(PATH + FILENAME);
  }

  public OrderModel[] getOrdersFromFile() {

    ArrayList<String> orderString = ORDER_FILE.readFile();
    OrderModel[] result = new OrderModel[orderString.size()];
    ArrayList<OrderLineModel> orderLines;

    for (int i = 0; i < orderString.size(); i++) {
      String[] splitValues = orderString.get(i).split(";");

      orderLines = stringToArrayList(splitValues[1]);

      result[i] =
          new OrderModel(LocalDateTime.parse(splitValues[0]), orderLines, splitValues[2],
              LocalDateTime.parse(splitValues[3]), Integer.parseInt(splitValues[4]));

    }

    return result;
  }

  public ArrayList<OrderLineModel> stringToArrayList(String s) {
    ArrayList<OrderLineModel> orderLines = new ArrayList<>();

    for (int j = 0; j < 2; j++) {
      String[] splitValues2 = s.split("@");
      orderLines.add(new OrderLineModel(Integer.parseInt(splitValues2[0]), splitValues2[1], Integer.parseInt(splitValues2[2])));
    }
    return orderLines;
  }

  public void saveOrdersToFile(OrderModel[] orders) {

    String[] result = new String[orders.length];

    for (int i = 0; i < result.length; i++) {
      result[i] =
          String.join(
              ";",
              orders[i].getTimeOfOrder().toString(),
              orders[i].getOrderLines().get(i).getFormattedOrderLine(),
              orders[i].getOrderId(),
              orders[i].getExpectedPickUpTime().toString(),
              String.valueOf(orders[i].getOrderStatus()));
    }
    ORDER_FILE.writeFile(result);
  }
}
