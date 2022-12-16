package com.hm.digital.common.enums;

import lombok.Getter;

@Getter
public enum ConfigEnum {
  /**
   * 发起点名次数
   */
  ROLL_CALL("rollcall", "发起点名次数");

  /**
   * 请求参数key
   */
  String key;

  /**
   * 请求参数备注
   */
  String value;

  ConfigEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
