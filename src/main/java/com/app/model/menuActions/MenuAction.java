package com.app.model.menuActions;

public abstract class MenuAction {
 private boolean keepRunning = true; //TODO RENAME
 private String description;

 public MenuAction(String description){
  this.description = description;
 }


 /**
  * Used for manually setting keepRunning to false.
  * @param keepRunning
  */


 public MenuAction(boolean keepRunning, String description){
  this.keepRunning = keepRunning;
  this.description = description;
 }


 public abstract void run();

 /**
  *
  * @return returns wether or not to keep running. Preferably use the keepRunning boolean.
  * this.getKeepRunning();
  */

 public boolean isKeepRunning(){
  return keepRunning;
 }

 public String getDescription() {
  return description;
 }

}
