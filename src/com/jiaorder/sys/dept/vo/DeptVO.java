package com.jiaorder.sys.dept.vo;

import com.pabula.fw.utility.VO;

/**
 * 部门VO
 *
 * @author cwq
 *         2016-03-28 10:38:31
 */
public class DeptVO implements VO {

    public String ROOT_DEPT_ID = "100";//根部门

    public static final int HAS_DELETED = 1; // 已经删除
    public static final int NOT_DELETE = -1; // 没有删除

    public DeptVO() {
        this.STATE = 0;
        this.IS_DEL = NOT_DELETE;
        this.PATH = "";
        this.ROOT_ID = ROOT_DEPT_ID;
        this.DEPTH = 0;
        this.DEPT_ID = DeptVO.NOT_DELETE;
    }

    //DEPT_ID
    int DEPT_ID;

    //SERVICE_ID
    int SERVICE_ID;

    //名称 名称 NAME
    String DEPT_NAME;

    //父部门
    int PARENT_DEPT_ID;

    //深度
    int DEPTH;

    //根部门编号
    String ROOT_ID;

    //部门路径
    String PATH;

    //删除状态
    int IS_DEL;

    //状态
    int STATE;

    //排序编号
    int ORDER_NUM;

    public int getIS_DEL() {
        return IS_DEL;
    }

    public void setIS_DEL(int IS_DEL) {
        this.IS_DEL = IS_DEL;
    }

    /**
     * @return 返回 DEPT_ID DEPT_ID
     */
    public int getDEPT_ID() {
        return DEPT_ID;
    }

    /**
     * @param DEPT_ID 设置 DEPT_ID 的值
     */
    public void setDEPT_ID(int DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    /**
     * @return 返回 SERVICE_ID SERVICE_ID
     */
    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    /**
     * @param SERVICE_ID 设置 SERVICE_ID 的值
     */
    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    /**
     * @return 返回 DEPT_NAME 名称 名称 NAME
     */
    public String getDEPT_NAME() {
        return DEPT_NAME;
    }

    /**
     * @param DEPT_NAME 设置 DEPT_NAME 的值
     */
    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }

    /**
     * @return 返回 PARENT_DEPT_ID 父部门
     */
    public int getPARENT_DEPT_ID() {
        return PARENT_DEPT_ID;
    }

    /**
     * @param PARENT_DEPT_ID 设置 PARENT_DEPT_ID 的值
     */
    public void setPARENT_DEPT_ID(int PARENT_DEPT_ID) {
        this.PARENT_DEPT_ID = PARENT_DEPT_ID;
    }

    /**
     * @return 返回 DEPTH 深度
     */
    public int getDEPTH() {
        return DEPTH;
    }

    /**
     * @param DEPTH 设置 DEPTH 的值
     */
    public void setDEPTH(int DEPTH) {
        this.DEPTH = DEPTH;
    }

    /**
     * @return 返回 ROOT_ID 根部门编号
     */
    public String getROOT_ID() {
        return ROOT_ID;
    }

    /**
     * @param ROOT_ID 设置 ROOT_ID 的值
     */
    public void setROOT_ID(String ROOT_ID) {
        this.ROOT_ID = ROOT_ID;
    }

    /**
     * @return 返回 PATH 部门路径
     */
    public String getPATH() {
        return PATH;
    }

    /**
     * @param PATH 设置 PATH 的值
     */
    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    /**
     * @return 返回 STATE 状态
     */
    public int getSTATE() {
        return STATE;
    }

    /**
     * @param STATE 设置 STATE 的值
     */
    public void setSTATE(int STATE) {
        this.STATE = STATE;
    }

    /**
     * @return 返回 ORDER_NUM 排序编号
     */
    public int getORDER_NUM() {
        return ORDER_NUM;
    }

    /**
     * @param ORDER_NUM 设置 ORDER_NUM 的值
     */
    public void setORDER_NUM(int ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
    }

    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("toString : ");
        ret.append(", DEPT_ID='" + DEPT_ID + "'");
        ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
        ret.append(", DEPT_NAME='" + DEPT_NAME + "'");
        ret.append(", PARENT_DEPT_ID='" + PARENT_DEPT_ID + "'");
        ret.append(", DEPTH='" + DEPTH + "'");
        ret.append(", ROOT_ID='" + ROOT_ID + "'");
        ret.append(", PATH='" + PATH + "'");
        ret.append(", STATE='" + STATE + "'");
        ret.append(", ORDER_NUM='" + ORDER_NUM + "'");
        return ret.toString();
    }
}