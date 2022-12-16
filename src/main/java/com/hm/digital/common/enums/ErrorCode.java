package com.hm.digital.common.enums;

public enum ErrorCode {

  NULL_OBJ(001,"对象为空"),
  ERROR_OBJ(500,"系统错误"),
  UNKNOWN_ERROR(999,"系统繁忙，请稍后再试....");

  private int value;
  private String desc;

  private ErrorCode(int value, String desc) {
    this.setValue(value);
    this.setDesc(desc);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return "[" + this.value + "]" + this.desc;
  }
}