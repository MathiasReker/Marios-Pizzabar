package com.app.controllers;

import com.app.models.StatisticsModel;
import com.app.views.StatisticsView;

import java.io.FileNotFoundException;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;

  public StatisticsController() {
    try {
      statisticsModel = new StatisticsModel();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.printInline("File does not exists.");
    }
  }

  public void viewStatistics() {
    STATISTIC_VIEW.printStatistics("Orders today:", statisticsModel.countOrdersToday());
    STATISTIC_VIEW.printStatistics("Average orders per hour:", statisticsModel.salesPerHour());
    try {
      STATISTIC_VIEW.printStatistics("Average sales per day:", statisticsModel.totalSalesPerDay());
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
    }

    STATISTIC_VIEW.printHeader("Sales per item:");
    STATISTIC_VIEW.printStatistics(statisticsModel.menuItems(), statisticsModel.salesPerItemPerDay());
  }
}
