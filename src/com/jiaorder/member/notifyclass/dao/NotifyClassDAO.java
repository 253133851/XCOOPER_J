package com.jiaorder.member.notifyclass.dao;

import com.jiaorder.member.notifyclass.vo.NotifyClassVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 通知分类表DAO
 *
 * @author zdk
 *         2016-03-21 15:33:41
 */
public class NotifyClassDAO {

    /**
     * 添加通知分类表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean addNotifyClass(NotifyClassVO VO) {

        try {
            if(checkRepeat(VO)) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("NOTIFY_CLASS");
                sh.setInsertForInt("NOTIFY_CLASS_ID", VO.getNOTIFY_CLASS_ID());//NOTIFY_CLASS_ID
                sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setInsertForString("NAME", VO.getNAME());//通知分类名
                sh.setInsertForInt("ORDER_NUM", VO.getORDER_NUM());//类别排序值
                sh.setInsertForInt("IS_DEL", VO.getIS_DEL());//是否删除
                sh.setInsertForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否为默认
                sh.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());
                sh.insert(ResourceManager.getConnection(), "添加通知分类表");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改通知分类表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean modifyNotifyClass(int serviceId, NotifyClassVO VO) {
        try {
            if(checkRepeat(VO)) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("NOTIFY_CLASS");
                sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setColumnForString("NAME", VO.getNAME());//通知分类名
                sh.setColumnForInt("ORDER_NUM", VO.getORDER_NUM());//类别排序值
                sh.setColumnForInt("IS_DEL", VO.getIS_DEL());//是否删除
                sh.setColumnForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否为默认
                sh.setWhereForInt("NOTIFY_CLASS_ID", " = ", VO.getNOTIFY_CLASS_ID());//NOTIFY_CLASS_ID
                sh.setWhereForInt("SERVICE_ID", " = ", serviceId);
                sh.update(ResourceManager.getConnection(), "修改通知分类表");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除通知分类表
     *
     * @param NOTIFY_CLASS_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean delNotifyClass(int serviceId, int NOTIFY_CLASS_ID) {
        try {
            SqlHelper sh = new SqlHelper();
            sh.setTable("NOTIFY_CLASS");
            sh.setWhereForInt("NOTIFY_CLASS_ID", " = ", NOTIFY_CLASS_ID);//NOTIFY_CLASS_ID
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sh.delete(ResourceManager.getConnection(), "删除通知分类表");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有单位
     */
    public List<NotifyClassVO> queryNotifyClass(int serviceId) {
        List<NotifyClassVO> notifyClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("NOTIFY_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("IS_DEL", " = ", NotifyClassVO.NOT_DELETE);
            sqlHelper.setORDER("ORDER_NUM ASC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            notifyClassVOs = getNotifyClassColl(sql);
            if (notifyClassVOs.size() > 0) {
                return notifyClassVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 判断重复数据
     * @return
     */
    public boolean checkRepeat(NotifyClassVO classVO) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("NOTIFY_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", classVO.getSERVICE_ID());
            sqlHelper.setWhereForInt("NOTIFY_CLASS_ID", " != ", classVO.getNOTIFY_CLASS_ID());
            sqlHelper.setWhereForString("NAME", " = ", classVO.getNAME());
            sqlHelper.setWhereForInt("IS_DEL", " = ", NotifyClassVO.NOT_DELETE);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<NotifyClassVO> list = getNotifyClassColl(sql);
            if (list.size() > 0) {
                return false;
            }
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据SQL获取通知分类表集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public List<NotifyClassVO> getNotifyClassColl(String sql) throws DataAccessException {

        List<NotifyClassVO> resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                NotifyClassVO VO = new NotifyClassVO();

                VO.setNOTIFY_CLASS_ID(rs.getInt("NOTIFY_CLASS_ID"));    //NOTIFY_CLASS_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setNAME(rs.getString("NAME"));    //通知分类名
                VO.setORDER_NUM(rs.getInt("ORDER_NUM"));    //类别排序值
                VO.setIS_DEL(rs.getInt("IS_DEL"));    //是否删除
                VO.setIS_DEFAULT(rs.getInt("IS_DEFAULT"));    //是否为默认
                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得通知分类表集合", e);
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
        return resultList;
    }

    /**
     * 根据ID取其VO
     *
     * @param NOTIFY_CLASS_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public NotifyClassVO getNotifyClassByID(int serviceId, int NOTIFY_CLASS_ID) {
        Collection coll = null;
        try {
            SqlHelper sh = new SqlHelper();
            sh.setSelectColumn("*");
            sh.setTable("NOTIFY_CLASS");
            sh.setWhereForInt("NOTIFY_CLASS_ID", " = ", NOTIFY_CLASS_ID);//NOTIFY_CLASS_ID
            sh.setWhereForInt("IS_DEL", " = ", NotifyClassVO.NOT_DELETE);
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);
            String sql = sh.getSQL(sh.getSelectSQL());
            coll = getNotifyClassColl(sql);
            Iterator it = coll.iterator();
            if (it.hasNext()) {
                return (NotifyClassVO) it.next();
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new NotifyClassVO();
    }
}