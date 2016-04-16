package com.jiaorder.shop.product.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.xcooper.ENV;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.pabula.common.util.StrUtil;

import java.sql.Timestamp;

/**
 * Created by sunsai on 2016/2/24.
 *
 * 这个呢，就是面向json的javaBean 注意，和数据库不是一一对应的呀！
 *
 */
@JsonFilter(ENV.JSON_FILTER_NAME)
public class ProductJson {

  public ProductJson() {

  }

  public void setProduct(ProductVO vo) {
    prdId = vo.getPRD_ID();
    serviceId = vo.getSERVICE_ID();
    orderNum = vo.getORDER_NUM();
    productIsSale = vo.getIS_SALE();
    saleValue = vo.getSALES_VOLUME();
    prdSpu = vo.getPRD_SPU();
    prdName = vo.getPRD_NAME();
    prdOtherName = vo.getPRD_OTHER_NAME();
    tag1 = vo.getTAG1();
    tag2 = vo.getTAG2();
    tag3 = vo.getTAG3();
    tag4 = vo.getTAG4();
    tag5 = vo.getTAG5();

    brandId = vo.getBRAND_ID();
    unitId = vo.getUNIT_ID();
    classId = vo.getCLASS_ID();


    img = vo.getIMG();

    keyword = vo.getKEYWORD();

    adImg1 = vo.getAD_IMG1();
    adImg2 = vo.getAD_IMG2();
    adImg3 = vo.getAD_IMG3();
    adImg4 = vo.getAD_IMG4();
    adImg5 = vo.getAD_IMG5();
    adImg6 = vo.getAD_IMG6();
    adImg7 = vo.getAD_IMG7();
    adImg8 = vo.getAD_IMG8();
    adImg9 = vo.getAD_IMG9();
    adImg10 = vo.getAD_IMG10();

    addDateTime = vo.getADD_DATETIME();
    updateDateTime = vo.getUPDATE_DATETIME();

    maxPrice = vo.getMAX_PRICE();
    minPrice = vo.getMIN_PRICE();
    isSku = vo.getIS_SKU();

  }

  public void setSku(ProductSkuVO vo) {
    stock = vo.getSTOCK();
    prdSku = vo.getPRD_SKU();
    skuId = vo.getSKU_ID();
    skuIsSale = vo.getIS_SALE();
    skuImage = vo.getIMG();
    skuName1 = vo.getSKU_NAME1();
    skuName2 = vo.getSKU_NAME2();
    skuName3 = vo.getSKU_NAME3();
    skuContent1 = vo.getSKU_CONTENT1();
    skuContent2 = vo.getSKU_CONTENT2();
    skuContent3 = vo.getSKU_CONTENT3();
    oldPrice = vo.getOLD_PRICE();
    price = vo.getPRICE();
    cost = vo.getCOST();
    barCode = vo.getBAR_CODE();
    skuSaleValue = vo.getSALES_VOLUME();
    skuShowName = "";

    if (StrUtil.isNotNull(skuName1) && StrUtil.isNotNull(skuContent1)) {
      skuShowName = "【"+ skuName1+"】:" + skuContent1;
    }
    if (StrUtil.isNotNull(skuName2) && StrUtil.isNotNull(skuContent2)) {
      skuShowName += " 【"+ skuName2+"】:" + skuContent2;
    }
    if (StrUtil.isNotNull(skuName3) && StrUtil.isNotNull(skuContent3)) {
      skuShowName += " 【"+ skuName3+"】:" + skuContent3;
    }

  }

  private int prdId;

  private int serviceId;

  private String unitName;
  private int unitId;

  private String brandName;
  private int brandId;

  private int orderNum;
  private int productIsSale;
  private int saleValue;
  private int classId;


  //库存
  private int stock;

  private String prdSpu;

  private String prdName;

  private String prdOtherName;

  private String tag1;
  private String tag2;
  private String tag3;
  private String tag4;
  private String tag5;

  private String img;

  private String keyword;

  private String saleTitle;


  private int skuId;

  private String skuShowName;


  private String adImg1;
  private String adImg2;
  private String adImg3;
  private String adImg4;
  private String adImg5;
  private String adImg6;
  private String adImg7;
  private String adImg8;
  private String adImg9;
  private String adImg10;

  private Timestamp addDateTime;
  private Timestamp updateDateTime;
  private int maxPrice;
  private int minPrice;
  private int isSku;



  private int skuIsSale;
  private int skuSaleValue;
  private String prdSku;
  private String skuName1;
  private String skuContent1;
  private String skuName2;
  private String skuContent2;
  private String skuName3;
  private String skuContent3;
  private int oldPrice;
  private int price;

  //成本价
  private int cost;
  private String skuImage;
  private String barCode;

  public String getSkuShowName() {
    return skuShowName;
  }

  public int getSkuId() {
    return skuId;
  }

  public int getMaxPrice() {
    return maxPrice;
  }

  public int getMinPrice() {
    return minPrice;
  }

  public int getIsSku() {
    return isSku;
  }

  public int getSkuIsSale() {
    return skuIsSale;
  }

  public int getSkuSaleValue() {
    return skuSaleValue;
  }

  public String getPrdSku() {
    return prdSku;
  }

  public String getSkuName1() {
    return skuName1;
  }

  public String getSkuContent1() {
    return skuContent1;
  }

  public String getSkuName2() {
    return skuName2;
  }

  public String getSkuContent2() {
    return skuContent2;
  }

  public String getSkuName3() {
    return skuName3;
  }

  public String getSkuContent3() {
    return skuContent3;
  }



  public int getPrice() {
    return price;
  }

  public int getCost() {
    return cost;
  }

  public String getSkuImage() {
    return skuImage;
  }

  public String getBarCode() {
    return barCode;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public int getPrdId() {
    return prdId;
  }

  public int getServiceId() {
    return serviceId;
  }

  public String getUnitName() {
    return unitName;
  }

  public String getBrandName() {
    return brandName;
  }

  public int getOrderNum() {
    return orderNum;
  }

  public int getProductIsSale() {
    return productIsSale;
  }

  public int getSaleValue() {
    return saleValue;
  }

  public int getStock() {
    return stock;
  }

  public String getPrdSpu() {
    return prdSpu;
  }

  public String getPrdName() {
    return prdName;
  }

  public String getPrdOtherName() {
    return prdOtherName;
  }

  public String getTag1() {
    return tag1;
  }

  public String getTag2() {
    return tag2;
  }

  public String getTag3() {
    return tag3;
  }

  public String getTag4() {
    return tag4;
  }

  public String getTag5() {
    return tag5;
  }

  public String getImg() {
    return img;
  }

  public String getKeyword() {
    return keyword;
  }

  public String getSaleTitle() {
    return saleTitle;
  }

  public String getAdImg1() {
    return adImg1;
  }

  public String getAdImg2() {
    return adImg2;
  }

  public String getAdImg3() {
    return adImg3;
  }

  public String getAdImg4() {
    return adImg4;
  }

  public String getAdImg5() {
    return adImg5;
  }

  public String getAdImg6() {
    return adImg6;
  }

  public String getAdImg7() {
    return adImg7;
  }

  public String getAdImg8() {
    return adImg8;
  }

  public String getAdImg9() {
    return adImg9;
  }

  public String getAdImg10() {
    return adImg10;
  }

  public Timestamp getAddDateTime() {
    return addDateTime;
  }

  public Timestamp getUpdateDateTime() {
    return updateDateTime;
  }

  public int getUnitId() {
    return unitId;
  }

  public int getBrandId() {
    return brandId;
  }

  public int getClassId() {
    return classId;
  }

  public String toString() {
    return "prdId:" + getPrdId() +", prdName:" + prdName +", skuId:" + getSkuId();
  }
}
