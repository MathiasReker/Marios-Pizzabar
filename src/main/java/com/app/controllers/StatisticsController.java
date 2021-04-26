package com.app.controllers;

import com.app.models.StatisticsModel;
import com.app.views.StatisticsView;

import java.io.IOException;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;

  public StatisticsController() {
    try {
      statisticsModel = new StatisticsModel();
    } catch (IOException e) {
      STATISTIC_VIEW.printInlineWarning(e.getMessage());
    }
  }

  public void viewStatistics() {
    STATISTIC_VIEW.printStatistics("Orders today:", statisticsModel.countOrdersToday());
    STATISTIC_VIEW.printStatistics("Average orders per hour:", statisticsModel.salesPerHour());
    try {
      STATISTIC_VIEW.printStatistics("Average sales per day:", statisticsModel.totalSalesPerDay());
    } catch (IOException e) {
      STATISTIC_VIEW.printWarning(e.getMessage());
    }

    STATISTIC_VIEW.printHeader("Sales per item today:");
    STATISTIC_VIEW.printStatistics(statisticsModel.menuItems(), statisticsModel.salesPerItemPerDay());
  }
}
