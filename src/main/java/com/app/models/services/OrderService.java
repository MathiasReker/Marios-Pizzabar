package com.app.models.services;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.OrderStatusKeys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderService {
  private final FileService FILE_SERVICE;

  public OrderService() throws IOException {
    String path = new ConfigService("orderDb").getPath();
    String filename = LocalDate.now() + ".txt";
    FILE_SERVICE = new FileService(path + filename);
  }

  public ArrayList<OrderModel> getOrdersFromFile() throws IOException {
    ArrayList<String> orderString = FILE_SERVICE.readFile();
    ArrayList<OrderModel> result = new ArrayList<>();
    ArrayList<OrderLineModel> orderLines;

    for (String s : orderString) {
      String[] splitValues = s.split(";");

      orderLines = getOrderLineFromString(splitValues[1]);

      OrderStatusKeys orderStatus = convertToOrderStatus(splitValues[4]);

      result.add(
          new OrderModel(LocalDateTime.parse(splitValues[0]), orderLines, Integer.parseInt(splitValues[2]),
              LocalDateTime.parse(splitValues[3]), orderStatus));
    }

    return result;
  }

  public ArrayList<OrderLineModel> getOrderLineFromString(String s) throws IOException {
    ArrayList<OrderLineModel> result = new ArrayList<>();
    String[] orderLineString = s.split("%");

    for (String value : orderLineString) {
      String[] splitValues2 = value.split("@");
      ItemModel itemModel = item(splitValues2[1]);
      result.add(new OrderLineModel(Integer.parseInt(splitValues2[0]), itemModel));
    }

    return result;
  }

  public void saveOrdersToFile(ArrayList<OrderModel> orders) throws FileNotFoundException {
    String[] result = new String[orders.size()];

    for (int i = 0; i < result.length; i++) {
      result[i] =
          String.join(
              ";",
              orders.get(i).getTimeOfOrder().toString(),
              convertArrayToString(orders.get(i).getOrderLines()),
              String.valueOf(orders.get(i).getOrderId()),
              orders.get(i).getExpectedPickUpTime().toString(),
              String.valueOf(orders.get(i).getOrderStatus()));
    }
    FILE_SERVICE.writeFile(result);
  }

  public ItemModel item(String itemId) throws IOException {
    String path = new ConfigService("itemDb").getPath();
    final ItemService ITEM_PARSER = new ItemService(path);
    ItemModel[] itemModels = ITEM_PARSER.getItemsFromFile();

    for (ItemModel itemModel : itemModels) {
      if (itemId.equals(itemModel.getId())) {
        return itemModel;
      }
    }

    return null;
  }

  public String convertArrayToString(ArrayList<OrderLineModel> orderLineModel) {
    StringBuilder result = new StringBuilder();
    for (OrderLineModel lineModel : orderLineModel) {
      result
          .append(lineModel.getQty())
          .append("@")
          .append(lineModel.getItem().getId())
          .append("%");
    }

    return result.toString();
  }

  public OrderStatusKeys convertToOrderStatus(String s) {
    if (s.equals("ACTIVE")) {
      return OrderStatusKeys.ACTIVE;
    }
    if (s.equals("COMPLETE")) {
      return OrderStatusKeys.COMPLETE;
    }
    if (s.equals("CANCELLED")) {
      return OrderStatusKeys.CANCELLED;
    }

    return null;
  }
}
