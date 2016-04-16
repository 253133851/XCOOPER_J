package com.jiaorder.shop.brand.vo;

import com.pabula.fw.utility.VO;

/**
 *品牌vo
 */
public class BrandVO implements VO {

  //品牌ID
  private int BRAND_ID;
  //公司ID
  private int SERVICE_ID;
  //品牌名
  private String BRAND;

  public int getBRAND_ID() {
    return BRAND_ID;
  }

  public void setBRAND_ID(int BRAND_ID) {
    this.BRAND_ID = BRAND_ID;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public String getBRAND() {
    return BRAND;
  }

  public void setBRAND(String BRAND) {
    this.BRAND = BRAND;
  }
}
