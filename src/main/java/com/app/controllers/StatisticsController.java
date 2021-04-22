package com.app.controllers;

import com.app.models.StatisticsModel;
import com.app.views.StatisticsView;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private final StatisticsModel statisticsModel = new StatisticsModel();

  public void viewStatistics() {
    STATISTIC_VIEW.print("Total orders of today: " + statisticsModel.countOrdersToday());
    STATISTIC_VIEW.print("Orders per hour:" + statisticsModel.salesPerHour()); // TODO round to 1 decimal
    STATISTIC_VIEW.print("Total sales per day: " + statisticsModel.totalSalesPerDay()); // TODO
  }
}
