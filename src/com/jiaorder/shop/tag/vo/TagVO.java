package com.jiaorder.shop.tag.vo;

import com.pabula.fw.utility.VO;

/**
 * 标签vo
 */
public class TagVO implements VO {

  //标签id
  private int TAG_ID;
  //公司id
  private int SERVICE_ID;
  //标签名
  private String TAG;

  public int getTAG_ID() {
    return TAG_ID;
  }

  public void setTAG_ID(int Tag_ID) {
    this.TAG_ID = Tag_ID;
  }

  public int getSERVICE_ID() {
    return SERVICE_ID;
  }

  public void setSERVICE_ID(int SERVICE_ID) {
    this.SERVICE_ID = SERVICE_ID;
  }

  public String getTAG() {
    return TAG;
  }

  public void setTAG(String Tag) {
    this.TAG = Tag;
  }
}
