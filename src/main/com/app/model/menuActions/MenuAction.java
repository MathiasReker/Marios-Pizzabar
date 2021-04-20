package com.app.model.menuActions;

public abstract class MenuAction {
 private boolean keepRunning = true;

 public MenuAction(){}


 /**
  * Used for manually setting keepRunning to false.
  * @param keepRunning
  */

 public MenuAction(boolean keepRunning){
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
