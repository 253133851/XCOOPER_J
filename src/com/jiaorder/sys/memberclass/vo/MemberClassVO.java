package com.jiaorder.sys.memberclass.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.xcooper.ENV;
import com.pabula.fw.utility.VO;

/**
 * 用户分类(归属区)vo
 */
@JsonFilter(ENV.JSON_FILTER_NAME)
public class MemberClassVO implements VO {

    public static final int HAS_DELETED = 1; // 已经删除
    public static final int NOT_DELETE = -1; // 没有删除

    //用户分类id
    private int MEMBER_CLASS_ID;
    //公司id
    private int SERVICE_ID;
    //排序值
    private int CLASS_ORDER_NUM;
    //父分类id
    private int PARENT_CLASS_ID;
    //是否默认
    private int IS_DEFAULT;
    //删除状态
    private int IS_DEL;
    //状态
    private int STATE;
    //分类名称
    private String CLASS_NAME;
    //分类路径
    private String CLASS_PATH;

    public MemberClassVO() {
        this.IS_DEL = NOT_DELETE;
    }

    public int getSTATE() {
        return STATE;
    }

    public void setSTATE(int STATE) {
        this.STATE = STATE;
    }

    public int getIS_DEL() {
        return IS_DEL;
    }

    public void setIS_DEL(int IS_DEL) {
        this.IS_DEL = IS_DEL;
    }

    public int getIS_DEFAULT() {
        return IS_DEFAULT;
    }

    public void setIS_DEFAULT(int IS_DEFAULT) {
        this.IS_DEFAULT = IS_DEFAULT;
    }

    public String getCLASS_PATH() {
        return CLASS_PATH;
    }

    public void setCLASS_PATH(String CLASS_PATH) {
        this.CLASS_PATH = CLASS_PATH;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public int getPARENT_CLASS_ID() {
        return PARENT_CLASS_ID;
    }

    public void setPARENT_CLASS_ID(int PARENT_CLASS_ID) {
        this.PARENT_CLASS_ID = PARENT_CLASS_ID;
    }

    public int getCLASS_ORDER_NUM() {
        return CLASS_ORDER_NUM;
    }

    public void setCLASS_ORDER_NUM(int CLASS_ORDER_NUM) {
        this.CLASS_ORDER_NUM = CLASS_ORDER_NUM;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public int getMEMBER_CLASS_ID() {
        return MEMBER_CLASS_ID;
    }

    public void setMEMBER_CLASS_ID(int MEMBER_CLASS_ID) {
        this.MEMBER_CLASS_ID = MEMBER_CLASS_ID;
    }

}
