package com.xcooper.comment.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 业务审核流程VO
 *
 * @author zdk
 *         2016-04-28 11:33:28
 */
public class TopicVO implements VO {


    public TopicVO() {
        //默认值为-1
        this.IS_DONE = -1;
    }

    //TOPIC_ID
    int TOPIC_ID;

    //SERVICE_ID
    int SERVICE_ID;

    //PROJECT_ID
    int PROJECT_ID;

    //CREATE_ID
    int CREATE_ID;


    //TITLE
    String TITLE;

    //DESCRIBES
    String DESCRIBES;

    //IS_DONE
    int IS_DONE;

    //REMARK
    String REMARK;

    //ADD_DATETIME
    Timestamp ADD_DATETIME;

    //UPDATE_DATETIME
    Timestamp UPDATE_DATETIME;

    public int getPROJECT_ID() {
        return PROJECT_ID;
    }

    public void setPROJECT_ID(int PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }


    public int getCREATE_ID() {
        return CREATE_ID;
    }

    public void setCREATE_ID(int CREATE_ID) {
        this.CREATE_ID = CREATE_ID;
    }

    /**
     * @return 返回 TOPIC_ID TOPIC_ID
     */
    public int getTOPIC_ID() {
        return TOPIC_ID;
    }

    /**
     * @param TOPIC_ID 设置 TOPIC_ID 的值
     */
    public void setTOPIC_ID(int TOPIC_ID) {
        this.TOPIC_ID = TOPIC_ID;
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
     * @return 返回 TITLE TITLE
     */
    public String getTITLE() {
        return TITLE;
    }

    /**
     * @param TITLE 设置 TITLE 的值
     */
    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    /**
     * @return 返回 DESCRIBES DESCRIBES
     */
    public String getDESCRIBES() {
        return DESCRIBES;
    }

    /**
     * @param DESCRIBES 设置 DESCRIBES 的值
     */
    public void setDESCRIBES(String DESCRIBES) {
        this.DESCRIBES = DESCRIBES;
    }

    /**
     * @return 返回 IS_DONE IS_DONE
     */
    public int getIS_DONE() {
        return IS_DONE;
    }

    /**
     * @param IS_DONE 设置 IS_DONE 的值
     */
    public void setIS_DONE(int IS_DONE) {
        this.IS_DONE = IS_DONE;
    }

    /**
     * @return 返回 REMARK REMARK
     */
    public String getREMARK() {
        return REMARK;
    }

    /**
     * @param REMARK 设置 REMARK 的值
     */
    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    /**
     * @return 返回 ADD_DATETIME ADD_DATETIME
     */
    public Timestamp getADD_DATETIME() {
        return ADD_DATETIME;
    }

    /**
     * @param ADD_DATETIME 设置 ADD_DATETIME 的值
     */
    public void setADD_DATETIME(Timestamp ADD_DATETIME) {
        this.ADD_DATETIME = ADD_DATETIME;
    }

    /**
     * @return 返回 UPDATE_DATETIME UPDATE_DATETIME
     */
    public Timestamp getUPDATE_DATETIME() {
        return UPDATE_DATETIME;
    }

    /**
     * @param UPDATE_DATETIME 设置 UPDATE_DATETIME 的值
     */
    public void setUPDATE_DATETIME(Timestamp UPDATE_DATETIME) {
        this.UPDATE_DATETIME = UPDATE_DATETIME;
    }

    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("toString : ");
        ret.append(", TOPIC_ID='" + TOPIC_ID + "'");
        ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
        ret.append(", TITLE='" + TITLE + "'");
        ret.append(", DESCRIBES='" + DESCRIBES + "'");
        ret.append(", IS_DONE='" + IS_DONE + "'");
        ret.append(", REMARK='" + REMARK + "'");
        ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
        ret.append(", UPDATE_DATETIME='" + UPDATE_DATETIME + "'");
        return ret.toString();
    }
}