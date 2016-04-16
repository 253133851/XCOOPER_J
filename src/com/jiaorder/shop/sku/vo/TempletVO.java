package com.jiaorder.shop.sku.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * Created by sunsai on 2016/2/23.
 */
public class TempletVO implements VO {

  private int SKU_TEMPLET_ID;
  private int SERVICE_ID;
  private String SKU_NAME1;
  private String SKU_CONTENT1;
  private String SKU_NAME2;
  private String SKU_CONTENT2;
  private String SKU_NAME3;
  private String SKU_CONTENT3;
  private Timestamp ADD_DATETIME;
  private Timestamp UPDATE_DATETIME;

  public int getSKU_TEMPLET_ID() {
    return SKU_TEMPLET_ID;
  }

  public void setSKU_TEMPLET_ID(int SKU_TEMPLET_ID) {
    this.SKU_TEMPLET_ID = SKU_TEMPLET_ID;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public String getSKU_NAME1() {
    return SKU_NAME1;
  }

  public void setSKU_NAME1(String SKU_NAME1) {
    this.SKU_NAME1 = SKU_NAME1;
  }

  public String getSKU_CONTENT1() {
    return SKU_CONTENT1;
  }

  public void setSKU_CONTENT1(String SKU_CONTENT1) {
    this.SKU_CONTENT1 = SKU_CONTENT1;
  }

  public String getSKU_NAME2() {
    return SKU_NAME2;
  }

  public void setSKU_NAME2(String SKU_NAME2) {
    this.SKU_NAME2 = SKU_NAME2;
  }

  public String getSKU_CONTENT2() {
    return SKU_CONTENT2;
  }

  public void setSKU_CONTENT2(String SKU_CONTENT2) {
    this.SKU_CONTENT2 = SKU_CONTENT2;
  }

  public String getSKU_NAME3() {
    return SKU_NAME3;
  }

  public void setSKU_NAME3(String SKU_NAME3) {
    this.SKU_NAME3 = SKU_NAME3;
  }

  public String getSKU_CONTENT3() {
    return SKU_CONTENT3;
  }

  public void setSKU_CONTENT3(String SKU_CONTENT3) {
    this.SKU_CONTENT3 = SKU_CONTENT3;
  }

  public Timestamp getADD_DATETIME() {
    return ADD_DATETIME;
  }

  public void setADD_DATETIME(Timestamp ADD_DATETIME) {
    this.ADD_DATETIME = ADD_DATETIME;
  }

  public Timestamp getUPDATE_DATETIME() {
    return UPDATE_DATETIME;
  }

  public void setUPDATE_DATETIME(Timestamp UPDATE_DATETIME) {
    this.UPDATE_DATETIME = UPDATE_DATETIME;
  }
}
