package com.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderParserModel {
  private final String PATH;
  private final FileHandler ORDERFILE;
  private final String FILENAME = LocalDate.now().toString();


  public OrderParserModel(String path) {
    PATH = path + FILENAME;
    ORDERFILE = new FileHandler(PATH);
  }

  public OrderModel[] getOrdersFromFrile(){

    ArrayList<String> orderString = ORDERFILE.readFile();
    OrderModel [] result = new OrderModel[orderString.size()];
    ArrayList<OrderLineModel> orderLines;

    for (int i = 0; i <orderString.size() ; i++) {
      String[] splitValues = orderString.get(i).split(";");

      orderLines = stringToArrayList(splitValues[1]);

      result[i] =
          new OrderModel(LocalDateTime.parse(splitValues[0]), orderLines,splitValues[2],
              LocalDateTime.parse(splitValues[3]), Integer.parseInt(splitValues[4]));

    }
    return result;
  }

  public ArrayList<OrderLineModel> stringToArrayList(String s){
    String temp = s;
    ArrayList<OrderLineModel> orderLines = new ArrayList<>();

    for (int j = 0; j < 2; j++) {
      String[] splitValues2 = temp.split("@");
      orderLines.add( new OrderLineModel(Integer.parseInt(splitValues2[0]), splitValues2[1], Integer.parseInt(splitValues2[2])));
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
    ORDERFILE.writeFile(result);
  }

}
