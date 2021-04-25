package com.app.views;

import com.app.views.utils.ColorKeys;

public class StartView extends View {
  public void printHeader() {
    print(" __  __          _          ___ _            _              \n" +
        "|  \\/  |__ _ _ _(_)___ ___ | _ (_)________ _| |__  __ _ _ _ \n" +
        "| |\\/| / _` | '_| / _ (_-< |  _/ |_ /_ / _` | '_ \\/ _` | '_|\n" +
        "|_|  |_\\__,_|_| |_\\___/__/ |_| |_/__/__\\__,_|_.__/\\__,_|_|\n", ColorKeys.YELLOW);
  }
}
