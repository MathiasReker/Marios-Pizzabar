package com.app.controllers;

import com.app.models.StatisticsModel;
import com.app.views.StatisticsView;

import java.io.FileNotFoundException;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;

  {
    try {
      statisticsModel = new StatisticsModel();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
    }
  }

  public void viewStatistics() {
    STATISTIC_VIEW.print("Total orders of today: " + statisticsModel.countOrdersToday());
    STATISTIC_VIEW.printInline("Orders per hour: ");
    STATISTIC_VIEW.print(statisticsModel.salesPerHour());
    try {
      STATISTIC_VIEW.printInline("Total sales per day: ");
      STATISTIC_VIEW.print(statisticsModel.totalSalesPerDay());
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
    }
    // TODO: Add more statistics
  }
}
