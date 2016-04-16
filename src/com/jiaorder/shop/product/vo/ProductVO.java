package com.jiaorder.shop.product.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * Created by sunsai on 2016/2/23.
*/

public class ProductVO implements VO {

  private int PRD_ID;//商品id，数据库唯一

  //服务ID
  private int SERVICE_ID;


  //单位ID
  private int UNIT_ID;

  //类别ID
  private int CLASS_ID;

  //品牌ID
  private int BRAND_ID;

  //是否已经删除
  private int IS_DEL;

  //排序号
  private int ORDER_NUM;

  //是否上架销售
  private int IS_SALE;

  //总的销量
  private int SALES_VOLUME;

  //SPU 编码
  private String PRD_SPU;

  //商品名字
  private String PRD_NAME;

  //商品别名
  private String PRD_OTHER_NAME;


  private String TAG1;
  private String TAG2;
  private String TAG3;
  private String TAG4;
  private String TAG5;
  private String IMG;
  private String KEYWORD;
  private String SALES_TITLE;
  private String AD_IMG1;
  private String AD_IMG2;
  private String AD_IMG3;
  private String AD_IMG4;
  private String AD_IMG5;
  private String AD_IMG6;
  private String AD_IMG7;
  private String AD_IMG8;
  private String AD_IMG9;
  private String AD_IMG10;
  private String FILE_PATH1;
  private String FILE_PATH2;
  private String FILE_PATH3;
  private Timestamp ADD_DATETIME;
  private Timestamp UPDATE_DATETIME;
  private Timestamp DEL_DATETIME;

  private int MAX_PRICE;

  private int MIN_PRICE;

  private int IS_SKU;//是否有sku

  public int getIS_DEL() {
    return IS_DEL;
  }

  public void setIS_DEL(int IS_DEL) {
    this.IS_DEL = IS_DEL;
  }

  public Timestamp getDEL_DATETIME() {
    return DEL_DATETIME;
  }

  public void setDEL_DATETIME(Timestamp DEL_DATETIME) {
    this.DEL_DATETIME = DEL_DATETIME;
  }

  public int getMAX_PRICE() {
    return MAX_PRICE;
  }

  public void setMAX_PRICE(int MAX_PRICE) {
    this.MAX_PRICE = MAX_PRICE;
  }

  public int getMIN_PRICE() {
    return MIN_PRICE;
  }

  public void setMIN_PRICE(int MIN_PRICE) {
    this.MIN_PRICE = MIN_PRICE;
  }

  public int getIS_SKU() {
    return IS_SKU;
  }

  public void setIS_SKU(int IS_SKU) {
    this.IS_SKU = IS_SKU;
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

  public void setSERVICE_ID(int SEVICE_ID) {
    this.SERVICE_ID = SEVICE_ID;
  }

  public int getUNIT_ID() {
    return UNIT_ID;
  }

  public void setUNIT_ID(int UNIT_ID) {
    this.UNIT_ID = UNIT_ID;
  }

  public int getCLASS_ID() {
    return CLASS_ID;
  }

  public void setCLASS_ID(int CLASS_ID) {
    this.CLASS_ID = CLASS_ID;
  }

  public int getBRAND_ID() {
    return BRAND_ID;
  }

  public void setBRAND_ID(int BRAND_ID) {
    this.BRAND_ID = BRAND_ID;
  }

  public int getORDER_NUM() {
    return ORDER_NUM;
  }

  public void setORDER_NUM(int ORDER_NUM) {
    this.ORDER_NUM = ORDER_NUM;
  }

  public int getIS_SALE() {
    return IS_SALE;
  }

  public void setIS_SALE(int IS_SALE) {
    this.IS_SALE = IS_SALE;
  }

  public int getSALES_VOLUME() {
    return SALES_VOLUME;
  }

  public void setSALES_VOLUME(int SALES_VOLUME) {
    this.SALES_VOLUME = SALES_VOLUME;
  }

  public String getPRD_SPU() {
    return PRD_SPU;
  }

  public void setPRD_SPU(String PRD_SPU) {
    this.PRD_SPU = PRD_SPU;
  }

  public String getPRD_NAME() {
    return PRD_NAME;
  }

  public void setPRD_NAME(String PRD_NAME) {
    this.PRD_NAME = PRD_NAME;
  }

  public String getPRD_OTHER_NAME() {
    return PRD_OTHER_NAME;
  }

  public void setPRD_OTHER_NAME(String PRD_OTHER_NAME) {
    this.PRD_OTHER_NAME = PRD_OTHER_NAME;
  }

  public String getTAG1() {
    return TAG1;
  }

  public void setTAG1(String TAG1) {
    this.TAG1 = TAG1;
  }

  public String getTAG2() {
    return TAG2;
  }

  public void setTAG2(String TAG2) {
    this.TAG2 = TAG2;
  }

  public String getTAG3() {
    return TAG3;
  }

  public void setTAG3(String TAG3) {
    this.TAG3 = TAG3;
  }

  public String getTAG4() {
    return TAG4;
  }

  public void setTAG4(String TAG4) {
    this.TAG4 = TAG4;
  }

  public String getTAG5() {
    return TAG5;
  }

  public void setTAG5(String TAG5) {
    this.TAG5 = TAG5;
  }

  public String getIMG() {
    return IMG;
  }

  public void setIMG(String IMG) {
    this.IMG = IMG;
  }

  public String getKEYWORD() {
    return KEYWORD;
  }

  public void setKEYWORD(String KEYWORD) {
    this.KEYWORD = KEYWORD;
  }

  public String getSALES_TITLE() {
    return SALES_TITLE;
  }

  public void setSALES_TITLE(String SALES_TITLE) {
    this.SALES_TITLE = SALES_TITLE;
  }

  public String getAD_IMG1() {
    return AD_IMG1;
  }

  public void setAD_IMG1(String AD_IMG1) {
    this.AD_IMG1 = AD_IMG1;
  }

  public String getAD_IMG2() {
    return AD_IMG2;
  }

  public void setAD_IMG2(String AD_IMG2) {
    this.AD_IMG2 = AD_IMG2;
  }

  public String getAD_IMG3() {
    return AD_IMG3;
  }

  public void setAD_IMG3(String AD_IMG3) {
    this.AD_IMG3 = AD_IMG3;
  }

  public String getAD_IMG4() {
    return AD_IMG4;
  }

  public void setAD_IMG4(String AD_IMG4) {
    this.AD_IMG4 = AD_IMG4;
  }

  public String getAD_IMG5() {
    return AD_IMG5;
  }

  public void setAD_IMG5(String AD_IMG5) {
    this.AD_IMG5 = AD_IMG5;
  }

  public String getAD_IMG6() {
    return AD_IMG6;
  }

  public void setAD_IMG6(String AD_IMG6) {
    this.AD_IMG6 = AD_IMG6;
  }

  public String getAD_IMG7() {
    return AD_IMG7;
  }

  public void setAD_IMG7(String AD_IMG7) {
    this.AD_IMG7 = AD_IMG7;
  }

  public String getAD_IMG8() {
    return AD_IMG8;
  }

  public void setAD_IMG8(String AD_IMG8) {
    this.AD_IMG8 = AD_IMG8;
  }

  public String getAD_IMG9() {
    return AD_IMG9;
  }

  public void setAD_IMG9(String AD_IMG9) {
    this.AD_IMG9 = AD_IMG9;
  }

  public String getAD_IMG10() {
    return AD_IMG10;
  }

  public void setAD_IMG10(String AD_IMG10) {
    this.AD_IMG10 = AD_IMG10;
  }

  public String getFILE_PATH1() {
    return FILE_PATH1;
  }

  public void setFILE_PATH1(String FILE_PATH1) {
    this.FILE_PATH1 = FILE_PATH1;
  }

  public String getFILE_PATH2() {
    return FILE_PATH2;
  }

  public void setFILE_PATH2(String FILE_PATH2) {
    this.FILE_PATH2 = FILE_PATH2;
  }

  public String getFILE_PATH3() {
    return FILE_PATH3;
  }

  public void setFILE_PATH3(String FILE_PATH3) {
    this.FILE_PATH3 = FILE_PATH3;
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

  public String toString() {
    return "prdId:" + getPRD_ID() +", prdName:" + getPRD_NAME();
  }
}
