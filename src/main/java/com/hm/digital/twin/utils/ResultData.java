package com.hm.digital.twin.utils;

import java.io.Serializable;

public class ResultData<T> implements Serializable {
  private static final long serialVersionUID = 5778573516446596671L;
  private static int SUCCESS = 0;
  private static int FAIL = -1;
  private static String MSG_SUCCESS = "成功";
  private static String MSG_WARNING = "成功但有告警";
  private static String MSG_FAIL = "失败";
  private int code = 0;
  private String type;
  private String message;
  private T data;

  public ResultData() {
  }

  public ResultData(int code, String type, String message, T data) {
    this.code = code;
    this.type = type;
    this.message = message;
    this.data = data;
  }

  public static <T> ResultData<T> success(T data) {
    return new ResultData(SUCCESS, ResultData.ResponseType.TYPE_SUCCESS.getType(), MSG_SUCCESS, data);
  }

  public static <T> ResultData<T> success() {
    return new ResultData(SUCCESS, ResultData.ResponseType.TYPE_SUCCESS.getType(), MSG_SUCCESS, null);
  }

  public static <T> ResultData<T> success(String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_SUCCESS;
    return new ResultData(SUCCESS, ResultData.ResponseType.TYPE_SUCCESS.getType(), message, data);
  }

  public static <T> ResultData<T> info(int code, String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_SUCCESS;
    return new ResultData(code, ResultData.ResponseType.TYPE_INFO.getType(), message, data);
  }

  public static <T> ResultData<T> warning(int code, String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_WARNING;
    return new ResultData(code, ResultData.ResponseType.TYPE_WARNING.getType(), message, data);
  }

  public static <T> ResultData<T> error(int code, String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_FAIL;
    return new ResultData(code, ResultData.ResponseType.TYPE_ERROR.getType(), message, data);
  }

  public static <T> ResultData<T> fail(T data) {
    return new ResultData(FAIL, ResultData.ResponseType.TYPE_ERROR.getType(), MSG_FAIL, data);
  }

  public static <T> ResultData<T> fail(String message, T data) {
    message = message != null && message.length() > 0 ? message : MSG_FAIL;
    return new ResultData(FAIL, ResultData.ResponseType.TYPE_ERROR.getType(), message, data);
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  protected static enum ResponseType {
    TYPE_SUCCESS("success"),
    TYPE_INFO("info"),
    TYPE_WARNING("warning"),
    TYPE_ERROR("error");

    private String type;

    private ResponseType(String type) {
      this.type = type;
    }

    public String getType() {
      return this.type;
    }
  }
}
