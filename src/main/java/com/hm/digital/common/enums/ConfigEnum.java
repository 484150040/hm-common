package com.hm.digital.common.enums;

import lombok.Getter;

@Getter
public enum ConfigEnum {
  /**
   * 发起点名次数
   */
  ROLL_CALL("rollcall", "发起点名次数"),

  /**
   * icc请求路径
   */
  ICC_HOST("icc.host", "icc请求路径"),
  /**
   * iccClientId
   */
  ICC_CLIENTID("icc.clientId", "iccClientId"),
  /**
   * iccClientSecret
   */
  ICC_CLIENTSECRET("icc.clientSecret", "iccClientSecret"),
  /**
   * 综合开始时间
   */
  ZH_STARTTIME("zh.startTime", "综合开始时间"),
  /**
   * 综合mysql请求地址
   */
  ZH_HTTPGETCHART("zh.httpGetChart", "综合mysql请求地址"),
  /**
   * 综合Orecle请求路径
   */
  ZH_HTTPGETCHARTORECLE("zh.httpGetChartOrecle", "综合Orecle请求路径"),
  /**
   * 实战请求地址
   */
  ZH_ELECTRONICCALL("zh.electronicCall", "实战请求地址"),

  /**
   * 综合报警请求地址
   */
  ZH_ALARMORDER("zh.alarmOrder", "综合报警请求地址"),
  /**
   * 实战报警请求地址
   */
  ZH_ELECTRONICCALLSTART("zh.electronicCallStart", "实战报警请求地址"),

  /**
   * mqtt请求地址
   */
  TCP_HOST("tcp.host", "mqtt请求地址"),

  /**
   * mqtt配置信息
   */
  MQTT_CONFIGS_CLIENTS("mqtt-configs.clients", "mqtt配置信息"),


  /**
   * 科创请求地址
   */
  KC_IP("kc.ip", "无人机请求地址"),

  /**
   * 无人机请求地址
   */
  HANGAR("hangar", "无人机请求地址"),

  /**
   * 无人机用户名
   */
  HANGAR_USERNAME("hangar.username", "无人机用户名"),

  /**
   * 无人机密码
   */
  HANGAR_PASSWORD("hangar.password", "无人机密码"),

  /**
   * 无人机机库sn码
   */
  HANGAR_SNM("hangar.snm", "无人机机库sn码"),

  /**
   * 无人机获取 MQTT 配置信息
   */
  HANGAR_CONFIGURATION("hangar.configuration", "无人机获取 MQTT 配置信息"),

  /**
   * 大华ip地址
   */
  DH_IP("dh.ip", "大华ip地址"),
  /**
   * 大华端口号
   */
  DH_PORT("dh.port", "大华端口号"),

  /**
   * 大华用户名
   */
  DH_USERNAME("dh.userName", "大华用户名"),

  /**
   * 大华密码
   */
  DH_PASSWORD("dh.password", "大华密码"),
  /**
   * 大华token值
   */
  DH_TOKEN("dh.token", "大华token值"),

  /**
   * 综合列表请求路径
   */
  ZH_HTTPGETLIST("zh.httpGetList", "综合列表请求路径");

  /**
   * 请求参数key
   */
  String key;

  /**
   * 请求参数备注
   */
  String value;

  /**
   * 前端、后端标识
   */
  String universe;


  ConfigEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
