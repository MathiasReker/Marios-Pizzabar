package com.app.controllers.menuactions;

import com.app.controllers.StatisticsController;

public class ViewStatisticsMenuAction extends MenuActions {

  public ViewStatisticsMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new StatisticsController().viewStatistics();
  }
}
