package com.jiaorder.shop.productclass.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.xcooper.ENV;
import com.pabula.fw.utility.VO;

/**
 * 商品分类vo
 */

@JsonFilter(ENV.JSON_FILTER_NAME)
public class ProductClassVO implements VO {

    public static final int HAS_DELETED = 1; // 已经删除
    public static final int NOT_DELETE = -1; // 没有删除

    private int CLASS_ID;

    private int SERVICE_ID;
    //分类名
    private String CLASS_NAME;
    //排序值
    private int CLASS_ORDER_NUM;
    //父分类ID
    private int PARENT_CLASS_ID;
    //分类path
    private String CLASS_PATH;
    //分类图标
    private String CLASS_ICON;
    //删除状态
    private int IS_DEL;

    public ProductClassVO() {
        this.IS_DEL = NOT_DELETE;
    }

    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public int getCLASS_ORDER_NUM() {
        return CLASS_ORDER_NUM;
    }

    public void setCLASS_ORDER_NUM(int CLASS_ORDER_NUM) {
        this.CLASS_ORDER_NUM = CLASS_ORDER_NUM;
    }

    public int getPARENT_CLASS_ID() {
        return PARENT_CLASS_ID;
    }

    public void setPARENT_CLASS_ID(int PARENT_CLASS_ID) {
        this.PARENT_CLASS_ID = PARENT_CLASS_ID;
    }

    public String getCLASS_PATH() {
        return CLASS_PATH;
    }

    public void setCLASS_PATH(String CLASS_PATH) {
        this.CLASS_PATH = CLASS_PATH;
    }

    public String getCLASS_ICON() {
        return CLASS_ICON;
    }

    public void setCLASS_ICON(String CLASS_ICON) {
        this.CLASS_ICON = CLASS_ICON;
    }

    public int getIS_DEL() {
        return IS_DEL;
    }

    public void setIS_DEL(int IS_DEL) {
        this.IS_DEL = IS_DEL;
    }
}
