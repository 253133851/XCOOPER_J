package com.jiaorder.sys.memberlevel.vo;

import com.pabula.fw.utility.VO;

/**
 * 客户级别vo
 */
public class MemberLevelVO implements VO {

    //客户级别id
    int MEMBER_LEVEL_ID;
    //公司id
    int SERVICE_ID;
    //级别名称
    String LEVEL_NAME;
    //折扣
    String PRICE_OFF;
    //排序值
    int ORDER_NUM;
    //客户数量
    int MEMBER_COUNT;
    //是否默认
    int IS_DEFAULT;

    //初始化值
    public MemberLevelVO() {
        this.ORDER_NUM = 0;
        this.MEMBER_COUNT = 0;
        this.IS_DEFAULT = -1;
    }

    public int getMEMBER_LEVEL_ID() {
        return MEMBER_LEVEL_ID;
    }

    public void setMEMBER_LEVEL_ID(int MEMBER_LEVEL_ID) {
        this.MEMBER_LEVEL_ID = MEMBER_LEVEL_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getLEVEL_NAME() {
        return LEVEL_NAME;
    }

    public void setLEVEL_NAME(String LEVEL_NAME) {
        this.LEVEL_NAME = LEVEL_NAME;
    }

    public String getPRICE_OFF() {
        return PRICE_OFF;
    }

    public void setPRICE_OFF(String PRICE_OFF) {
        this.PRICE_OFF = PRICE_OFF;
    }

    public int getORDER_NUM() {
        return ORDER_NUM;
    }

    public void setORDER_NUM(int ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
    }

    public int getMEMBER_COUNT() {
        return MEMBER_COUNT;
    }

    public void setMEMBER_COUNT(int MEMBER_COUNT) {
        this.MEMBER_COUNT = MEMBER_COUNT;
    }

    public int getIS_DEFAULT() {
        return IS_DEFAULT;
    }

    public void setIS_DEFAULT(int IS_DEFAULT) {
        this.IS_DEFAULT = IS_DEFAULT;
    }

}
