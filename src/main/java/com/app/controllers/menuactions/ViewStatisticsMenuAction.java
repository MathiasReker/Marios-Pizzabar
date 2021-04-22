package com.app.controllers.menuactions;

import com.app.controllers.StatisticsController;

public class ViewStatisticsMenuAction extends MenuAction {

  public ViewStatisticsMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new StatisticsController().viewStatistics();
  }
}
