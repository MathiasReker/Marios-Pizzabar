package com.app.controllers.menuactions;

public abstract class MenuActions {
  private final String description;
  private boolean keepRunning = true;

  public MenuActions(String description) {
    this.description = description;
  }

  /**
   * Used for manually setting keepRunning to false.
   *
   * @param keepRunning boolean
   * @param description Name of the menu item
   */
  public MenuActions(boolean keepRunning, String description) {
    this.keepRunning = keepRunning;
    this.description = description;
  }

  public abstract void run();

  /**
   * @return returns whether or not to keep running. Preferably use the keepRunning boolean.
   * this.getKeepRunning();
   */
  public boolean isKeepRunning() {
    return keepRunning;
  }

  public String getDescription() {
    return description;
  }
}
