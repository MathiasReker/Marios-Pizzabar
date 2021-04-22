package com.app.model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderParserModel {

  private final FileHandler ORDER_FILE;
  private final String FILENAME = LocalDate.now() + ".txt";
  private final String PATH = new ConfigParserModel("orderDb").getPath();

  public OrderParserModel() throws FileNotFoundException {
    ORDER_FILE = new FileHandler(PATH + FILENAME);
  }

  public ArrayList<OrderModel> getOrdersFromFile() throws FileNotFoundException {
    ArrayList<String> orderString = ORDER_FILE.readFile();
    ArrayList<OrderModel> result = new ArrayList<>();
    ArrayList<OrderLineModel> orderLines;

    for (int i = 0; i < orderString.size(); i++) {
      String[] splitValues = orderString.get(i).split(";");

      orderLines = stringToOrderLine(splitValues[1]);

      result.add(
          new OrderModel(LocalDateTime.parse(splitValues[0]), orderLines, splitValues[2],
              LocalDateTime.parse(splitValues[3]), Integer.parseInt(splitValues[4])));

    }

    return result;
  }

  public ArrayList<OrderLineModel> stringToOrderLine(String s) throws FileNotFoundException {
    String temp = s;
    ArrayList<OrderLineModel> orderLines = new ArrayList<>();
    String [] orderLineString = temp.split("%");

    for (int j = 0; j < orderLineString.length; j++) {
      String[] splitValues2 = orderLineString[j].split("@");
      ItemModel itemModel = item(splitValues2[1]);
      orderLines.add(new OrderLineModel(Integer.parseInt(splitValues2[0]), itemModel));
    }
    return orderLines;
  }

  public void saveOrdersToFile(ArrayList<OrderModel> orders) {

    String[] result = new String[orders.size()];

    for (int i = 0; i < result.length; i++) {
      result[i] =
          String.join(
              ";",
              orders.get(i).getTimeOfOrder().toString(),
              convertArrayToString(orders.get(i).getOrderLines()),
              orders.get(i).getOrderId(),
              orders.get(i).getExpectedPickUpTime().toString(),
              String.valueOf(orders.get(i).getOrderStatus()));
    }
    ORDER_FILE.writeFile(result);
  }

  public ItemModel item(String itemId) throws FileNotFoundException {

    String path = new ConfigParserModel("itemDb").getPath();
    final ItemParser ITEM_PARSER = new ItemParser(path);
    ItemModel[] itemModels = ITEM_PARSER.getItemsFromFile();

    for (int i = 0; i < itemModels.length; i++) {
      if (itemId.equals(itemModels[i].getId())){
        return itemModels[i];
      }
    }
    return null;
  }

  public String convertArrayToString (ArrayList<OrderLineModel> orderLineModel){

    String temp = "";

    for (int i = 0; i < orderLineModel.size(); i++) {

      temp += orderLineModel.get(i).getQty() + "@" + orderLineModel.get(i).getItem().getId() + "%";


    }
    return temp;
  }

}
