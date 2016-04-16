package com.jiaorder.shop.sku.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * Created by sunsai on 2016/2/23.
 */
public class ProductSkuVO implements VO {


  private int SKU_ID;
  private int PRD_ID;
  private int SALES_VOLUME;
  private int STOCK;
  private int IS_SALE;
  private Timestamp ADD_DATETIME;
  private Timestamp UPDATE_DATETIME;
  private String PRD_SKU;
  private String SKU_NAME1;
  private String SKU_CONTENT1;
  private String SKU_NAME2;
  private String SKU_CONTENT2;
  private String SKU_NAME3;
  private String SKU_CONTENT3;
  private int OLD_PRICE;
  private int PRICE;
  private int COST;
  private String IMG;
  private String BAR_CODE;
  private int SERVICE_ID;
  private int IS_DEL;
  private Timestamp DEL_DATETIME;

  public Timestamp getDEL_DATETIME() {
    return DEL_DATETIME;
  }

  public void setDEL_DATETIME(Timestamp DEL_DATETIME) {
    this.DEL_DATETIME = DEL_DATETIME;
  }

  public int getIS_DEL() {
    return IS_DEL;
  }

  public void setIS_DEL(int IS_DEL) {
    this.IS_DEL = IS_DEL;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public int getSKU_ID() {
    return SKU_ID;
  }

  public void setSKU_ID(int SKU_ID) {
    this.SKU_ID = SKU_ID;
  }

  public int getPRD_ID() {
    return PRD_ID;
  }

  public void setPRD_ID(int PRD_ID) {
    this.PRD_ID = PRD_ID;
  }

  public int getSALES_VOLUME() {
    return SALES_VOLUME;
  }

  public void setSALES_VOLUME(int SALES_VOLUME) {
    this.SALES_VOLUME = SALES_VOLUME;
  }

  public int getSTOCK() {
    return STOCK;
  }

  public void setSTOCK(int STOCK) {
    this.STOCK = STOCK;
  }

  public int getIS_SALE() {
    return IS_SALE;
  }

  public void setIS_SALE(int IS_SALE) {
    this.IS_SALE = IS_SALE;
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

  public String getPRD_SKU() {
    return PRD_SKU;
  }

  public void setPRD_SKU(String PRD_SKU) {
    this.PRD_SKU = PRD_SKU;
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

  public int getOLD_PRICE() {
    return OLD_PRICE;
  }

  public void setOLD_PRICE(int OLD_PRICE) {
    this.OLD_PRICE = OLD_PRICE;
  }

  public int getPRICE() {
    return PRICE;
  }

  public void setPRICE(int PRICE) {
    this.PRICE = PRICE;
  }

  public int getCOST() {
    return COST;
  }

  public void setCOST(int COST) {
    this.COST = COST;
  }

  public String getIMG() {
    return IMG;
  }

  public void setIMG(String IMG) {
    this.IMG = IMG;
  }

  public String getBAR_CODE() {
    return BAR_CODE;
  }

  public void setBAR_CODE(String BAR_CODE) {
    this.BAR_CODE = BAR_CODE;
  }

}
