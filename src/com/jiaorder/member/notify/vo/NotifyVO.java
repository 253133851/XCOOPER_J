package com.jiaorder.member.notify.vo;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 通知公告VO
 *
 * @author cwq
 *         2016-03-21 15:02:59
 */
public class NotifyVO implements VO {

    public final static int TYPE_AD=1;

    public final static int TYPE_DEFAULT=2;


    //NOTIFY_ID
    int NOTIFY_ID;

    //SERVICE_ID
    int SERVICE_ID;

    //CLASS_ID
    int CLASS_ID;

    //通知标题
    String TITLE;

    //通知富文本
    String INTRO;

    //已读人数
    int READ_NUM;

    //可见性状态
    String NOTIFY_SHOW;

    //类型
    String TYPE;

    //广告图片
    String AD_IMG;

    //发布时间
    Timestamp ADD_DATETIME;

    //分类名
    String CLASS_NAME;


    //每页大小	//用于分页
    int PageSize;

    //当前页码
    int PageIndex;

    //过滤时间
    String FILTER_TIME;

    /**
     * 设置页面信息
     *
     * @param pageSize
     * @param PageIndex
     */
    public void setPageInfo(int pageSize, int PageIndex) {
        this.PageSize = pageSize;
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public String getFILTER_TIME() {
        return FILTER_TIME;
    }

    /**
     * 根据不同的过滤时间条件 设定好起始时间
     * @param FILTER_TIME
     */
    public void setFILTER_TIME(String FILTER_TIME) {
        if (FILTER_TIME.equals("day")) {
            this.FILTER_TIME = DateUtil.getXDaysBefore(1);
        } else if (FILTER_TIME.equals("week")) {
            this.FILTER_TIME = DateUtil.getXDaysBefore(7);
        } else if (FILTER_TIME.equals("month")) {
            this.FILTER_TIME = DateUtil.getXMonthsBefore(1);
        } else if (FILTER_TIME.equals("year")) {
            this.FILTER_TIME = DateUtil.getXYearsBefore(1);
        }
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    /**
     * @return 返回 NOTIFY_ID NOTIFY_ID
     */
    public int getNOTIFY_ID() {
        return NOTIFY_ID;
    }

    /**
     * @param NOTIFY_ID 设置 NOTIFY_ID 的值
     */
    public void setNOTIFY_ID(int NOTIFY_ID) {
        this.NOTIFY_ID = NOTIFY_ID;
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
     * @return 返回 CLASS_ID CLASS_ID
     */
    public int getCLASS_ID() {
        return CLASS_ID;
    }

    /**
     * @param CLASS_ID 设置 CLASS_ID 的值
     */
    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }

    /**
     * @return 返回 TITLE 通知标题
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
     * @return 返回 INTRO 通知富文本
     */
    public String getINTRO() {
        return INTRO;
    }

    /**
     * @param INTRO 设置 INTRO 的值
     */
    public void setINTRO(String INTRO) {
        this.INTRO = INTRO;
    }

    /**
     * @return 返回 READ_NUM 已读人数
     */
    public int getREAD_NUM() {
        return READ_NUM;
    }

    /**
     * @param READ_NUM 设置 READ_NUM 的值
     */
    public void setREAD_NUM(int READ_NUM) {
        this.READ_NUM = READ_NUM;
    }

    /**
     * @return 返回 NOTIFY_SHOW 可见性状态
     */
    public String getNOTIFY_SHOW() {
        return NOTIFY_SHOW;
    }

    /**
     * @param NOTIFY_SHOW 设置 NOTIFY_SHOW 的值
     */
    public void setNOTIFY_SHOW(String NOTIFY_SHOW) {
        this.NOTIFY_SHOW = NOTIFY_SHOW;
    }

    /**
     * @return 返回 TYPE 类型
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * @param TYPE 设置 TYPE 的值
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    /**
     * @return 返回 AD_IMG 广告图片
     */
    public String getAD_IMG() {
        return AD_IMG;
    }

    /**
     * @param AD_IMG 设置 AD_IMG 的值
     */
    public void setAD_IMG(String AD_IMG) {
        this.AD_IMG = AD_IMG;
    }

    public Timestamp getADD_DATETIME() {
        return ADD_DATETIME;
    }

    public void setADD_DATETIME(Timestamp ADD_DATETIME) {
        this.ADD_DATETIME = ADD_DATETIME;
    }

    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("toString : ");
        ret.append(", NOTIFY_ID='" + NOTIFY_ID + "'");
        ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
        ret.append(", CLASS_ID='" + CLASS_ID + "'");
        ret.append(", TITLE='" + TITLE + "'");
        ret.append(", INTRO='" + INTRO + "'");
        ret.append(", READ_NUM='" + READ_NUM + "'");
        ret.append(", NOTIFY_SHOW='" + NOTIFY_SHOW + "'");
        ret.append(", TYPE='" + TYPE + "'");
        ret.append(", AD_IMG='" + AD_IMG + "'");
        ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
        return ret.toString();
    }

}