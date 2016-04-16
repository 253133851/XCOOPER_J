package com.jiaorder.shop.productintro.vo;

import com.pabula.fw.utility.VO;

/**
 * Created by sunsai on 2016/2/23.
 */
public class ProductIntroVO implements VO {

  //INTRO_ID             ID                             not null,
  //PRD_ID               ID                             null,
  //SERVICE_ID           SERVICE_ID                     null,
  //PRD_INTRO            text                           null,

  private int INTRO_ID;
  private int PRD_ID;
  private int SERVICE_ID;
  private String PRD_INTRO;

  public int getINTRO_ID() {
    return INTRO_ID;
  }

  public void setINTRO_ID(int INTRO_ID) {
    this.INTRO_ID = INTRO_ID;
  }

  public int getPRD_ID() {
    return PRD_ID;
  }

  public void setPRD_ID(int PRD_ID) {
    this.PRD_ID = PRD_ID;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public String getPRD_INTRO() {
    return PRD_INTRO;
  }

  public void setPRD_INTRO(String PRD_INTRO) {
    this.PRD_INTRO = PRD_INTRO;
  }

}
