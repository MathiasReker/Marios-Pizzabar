package com.app.models.menuactions;

import com.app.controllers.StatisticsController;

public class ViewStatisticsMenuAction extends MenuAction {

  public ViewStatisticsMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new StatisticsController().viewStatistics();
  }
} // TODO move menuactions to controllers
