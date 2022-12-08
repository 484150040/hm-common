package com.hm.digital.common.enums;

import lombok.Getter;

@Getter
public enum InputParameterEnum {
  /**
   * 正提讯列表
   */
  NOW_ARRAIGN_LIST("nowArraignList", "正提讯列表"),

  /**
   * 正入所列表
   */
  NOW_TODAY_ENTRANCE_PRISON_LIST("nowTodayEntrancePrisonList", "正入所列表"),

  /**
   * 羁押码红色列表
   */
  DETENTION_RED_LIST("detentionRedList", "羁押码红色列表"),

  /**
   * 羁押码黄色列表
   */
  DETENTION_YELLOW_LIST("detentionYellowList", "羁押码黄色列表"),

  /**
   *今日监室违规列表
   */
  TODAY_PRISONER_BREAK_RULE_LIST("todayPrisonerBreakRuleList", "今日监室违规列表"),

  /**
   * 每日民警进监情况
   */
  TODAY_CIVILIAN_POLICE_IN_PRISONB_CHAR("todayCivilianPoliceInPrisonbChar", "每日民警进监情况"),

  /**
   * 每月民警进监情况
   */
  MONTH_CIVILIAN_POLICE_IN_PRISONB_CHAR("monthCivilianPoliceInPrisonbChar", "每月民警进监情况"),

  /**
   * 每季度民警进监情况
   */
  QUARTER_CIVILIAN_POLICE_IN_PRISONB_CHAR("quarterCivilianPoliceInPrisonbChar", "每季度民警进监情况"),

  /**
   * 每日各巡视点巡视时长
   */
  TODAY_INSPECTION_DURATION_CHAR("todayInspectionDurationChar", "每日各巡视点巡视时长"),

  /**
   * 每月各巡视点巡视时长
   */
  MONTH_INSPECTION_DURATION_CHAR("monthInspectionDurationChar", "每月各巡视点巡视时长"),

  /**
   * 每季度各巡视点巡视时长
   */
  QUARTER_INSPECTION_DURATION_CHAR("quarterInspectionDurationChar", "每季度各巡视点巡视时长"),

  /**
   * 每日医疗预约
   */
  TODAY_MEDICAL_APPOINTMENT("todayMedicalAppointment", "每日医疗预约"),
  /**
   * 每月医疗预约
   */
  MONTH_MEDICAL_APPOINTMENT("monthMedicalAppointment", "每月医疗预约"),
  /**
   * 每季度医疗预约
   */
  QUARTER_MEDICAL_APPOINTMENT("quarterMedicalAppointment", "每季度医疗预约"),
  /**
   * 每日押量
   */
  TODAY_DORM_CODE_IN_PRISON("todayDormCodeInPrison", "每日押量"),
  /**
   * 每月押量
   */
  MONTH_DORM_CODE_IN_PRISON("monthDormCodeInPrison", "每月押量"),
  /**
   * 每季度押量
   */
  QUARTER_DORM_CODE_IN_PRISON("quarterDormCodeInPrison", "每季度押量"),

  //监室交互
  /**
   * 每日民警进监情况
   */
  DORMCODE_TODAY_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormCodeTodayCivilianPoliceInPrisonbChar", "每日民警进监情况"),

  /**
   * 每月民警进监情况
   */
  DORMCODE_MONTH_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormCodeMonthCivilianPoliceInPrisonbChar", "每月民警进监情况"),

  /**
   * 每季度民警进监情况
   */
  DORMCODE_QUARTER_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormCodeQuarterCivilianPoliceInPrisonbChar", "每季度民警进监情况"),

  /**
   * 每日医疗预约
   */
  DORMCODE_TODAY_MEDICAL_APPOINTMENT("dormCodeTodayMedicalAppointment", "每日医疗预约"),
  /**
   * 每月医疗预约
   */
  DORMCODE_MONTH_MEDICAL_APPOINTMENT("dormCodeMonthMedicalAppointment", "每月医疗预约"),
  /**
   * 每季度医疗预约
   */
  DORMCODE_QUARTER_MEDICAL_APPOINTMENT("dormCodeQuarterMedicalAppointment", "每季度医疗预约"),
  /**
   * 每日押量
   */
  DORMCODE_TODAY_DORM_CODE_IN_PRISON("dormCodeTodayDormCodeInPrison", "每日押量"),
  /**
   * 每月押量
   */
  DORMCODE_MONTH_DORM_CODE_IN_PRISON("dormCodeMonthDormCodeInPrison", "每月押量"),
  /**
   * 每季度押量
   */
  DORMCODE_QUARTER_DORM_CODE_IN_PRISON("dormCodeQuarterDormCodeInPrison", "每季度押量"),

  //监区交互

  /**
   * 每日民警进监情况
   */
  DORMAREA_TODAY_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormAreaTodayCivilianPoliceInPrisonbChar", "每日民警进监情况"),

  /**
   * 每月民警进监情况
   */
  DORMAREA_MONTH_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormAreaMonthCivilianPoliceInPrisonbChar", "每月民警进监情况"),

  /**
   * 每季度民警进监情况
   */
  DORMAREA_QUARTER_CIVILIAN_POLICE_IN_PRISONB_CHAR("dormAreaQuarterCivilianPoliceInPrisonbChar", "每季度民警进监情况"),
  /**
   * 每日医疗预约
   */
  DORMAREA_TODAY_MEDICAL_APPOINTMENT("dormAreaTodayMedicalAppointment", "每日医疗预约"),
  /**
   * 每月医疗预约
   */
  DORMAREA_MONTH_MEDICAL_APPOINTMENT("dormAreaMonthMedicalAppointment", "每月医疗预约"),
  /**
   * 每季度医疗预约
   */
  DORMAREA_QUARTER_MEDICAL_APPOINTMENT("dormAreaQuarterMedicalAppointment", "每季度医疗预约"),
  /**
   * 每日押量
   */
  DORMAREA_TODAY_DORM_CODE_IN_PRISON("dormAreaTodayDormCodeInPrison", "每日押量"),
  /**
   * 每月押量
   */
  DORMAREA_MONTH_DORM_CODE_IN_PRISON("dormAreaMonthDormCodeInPrison", "每月押量"),
  /**
   * 每季度押量
   */
  DORMAREA_QUARTER_DORM_CODE_IN_PRISON("dormCodeQuarterDormCodeInPrison", "每季度押量"),

  /**
   * 重点人员- 风险人员
   */
  RISKLEVEL_PRISONER_CHART("riskLevelPrisonerChart", "重点人员- 风险人员");
  /**
   * 请求参数key
   */
  String key;

  /**
   * 请求参数备注
   */
  String value;

  InputParameterEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

}
