package com.app.models;

import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidatorModel {

  private final OrderView ORDER_VIEW = new OrderView();
  private final Scanner SCANNER = new Scanner(System.in);

  public int validInputInt() {

    int userInput;

    while (!SCANNER.hasNextInt()) {
      System.out.print("Not a valid input, please try again: ");
      SCANNER.next();
    }
    userInput = SCANNER.nextInt();
    SCANNER.nextLine();

    return userInput;
  }
}
