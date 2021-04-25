package com.app.models;

public enum OrderStatusKeys {
  ACTIVE(0),
  COMPLETE(1),
  CANCELLED(2);

private final int code;

OrderStatusKeys(int code){
  this.code = code;
}


}


