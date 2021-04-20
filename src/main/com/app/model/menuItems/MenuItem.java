package com.app.model.menuItems;

public abstract class MenuItem {
 private boolean keepRunning = true;

 public MenuItem(){}


 /**
  * Used for manually setting keepRunning to false.
  * @param keepRunning
  */

 public MenuItem(boolean keepRunning){
  this.keepRunning = keepRunning;
 }

 /**
  *
  * @return returns wether or not to keep running. Preferably use the keepRunning boolean.
  * this.getKeepRunning();
  */

 public abstract void run();

 public boolean isKeepRunning(){
  return keepRunning;
 }
}
