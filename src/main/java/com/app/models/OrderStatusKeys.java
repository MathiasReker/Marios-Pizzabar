package com.app.models;

public enum OrderStatusKeys {
  ACTIVE(0),
  COMPLETE(1),
  CANCELLED(2);

private final int status;

OrderStatusKeys(int status){
  this.status = status;
}
}
