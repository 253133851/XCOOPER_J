package com.jiaorder.shop.unit.vo;

import com.pabula.fw.utility.VO;

/**
 * 单位vo
 */
public class UnitVO implements VO {

  //单位id
  private int UNIT_ID;
  //公司id
  private int SERVICE_ID;
  //单位名
  private String UNIT;

  public int getUNIT_ID() {
    return UNIT_ID;
  }

  public void setUNIT_ID(int UNIT_ID) {
    this.UNIT_ID = UNIT_ID;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public String getUNIT() {
    return UNIT;
  }

  public void setUNIT(String UNIT) {
    this.UNIT = UNIT;
  }
}
