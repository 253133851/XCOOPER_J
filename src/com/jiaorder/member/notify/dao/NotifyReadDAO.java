package com.jiaorder.member.notify.dao;

import com.jiaorder.member.notify.vo.NotifyReadVO;
import com.jiaorder.shop.unit.vo.UnitVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
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
 * 通知已读表DAO
 *
 * @author zdk
 *         2016-03-21 15:31:16
 */
public class NotifyReadDAO {

    /**
     * 添加通知已读表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public boolean addNotifyRead(NotifyReadVO VO) throws DataAccessException {
        try {
            if (checkRepeat(VO.getSERVICE_ID(), VO.getTARGET_ID(), VO.getNOTIFY_ID())) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("NOTIFY_READ");
                sh.setInsertForInt("NOTIFY_READ_ID", VO.getNOTIFY_READ_ID());//NOTIFY_READ_ID
                sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setInsertForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
                sh.setInsertForString("TYPE", VO.getTYPE());//读取用户类型
                sh.setInsertForInt("TARGET_ID", VO.getTARGET_ID());//读取用户id
                sh.setInsertForDatetime("READ_DATETIME", DateUtil.format(VO.getREAD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//读取时间
                sh.setInsertForString("IP", VO.getIP());//ip
                sh.insert(ResourceManager.getConnection(), "添加通知已读表");
                return true;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return false;
    }

    /**
     * 修改通知已读表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public void modifyNotifyRead(NotifyReadVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY_READ");

            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
            sh.setColumnForString("TYPE", VO.getTYPE());//读取用户类型
            sh.setColumnForInt("TARGET_ID", VO.getTARGET_ID());//读取用户id
            sh.setColumnForDatetime("READ_DATETIME", DateUtil.format(VO.getREAD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//读取时间
            sh.setColumnForString("IP", VO.getIP());//ip

            sh.setWhereForInt("NOTIFY_READ_ID", " = ", VO.getNOTIFY_READ_ID());//NOTIFY_READ_ID

            sh.update(ResourceManager.getConnection(), "修改通知已读表");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除通知已读表
     *
     * @param NOTIFY_READ_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public void delNotifyRead(int NOTIFY_READ_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY_READ");

            sh.setWhereForInt("NOTIFY_READ_ID", " = ", NOTIFY_READ_ID);//NOTIFY_READ_ID

            sh.delete(ResourceManager.getConnection(), "删除通知已读表");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 判断重复数据
     *
     * @param serviceId
     * @return
     */
    public boolean checkRepeat(int serviceId, int userID, int NotifyId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("NOTIFY_READ");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("TARGET_ID", " = ", userID);
            sqlHelper.setWhereForInt("NOTIFY_ID", " = ", NotifyId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<UnitVO> list = (List<UnitVO>) getNotifyReadColl(sql);
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
     * 批量删除通知已读表
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public void delNotifyRead(Collection coll) throws DataAccessException {
        Iterator it = coll.iterator();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();

            while (it.hasNext()) {
                String ids = it.next().toString();
                String[] id = ids.split(",");

                SqlHelper sh = new SqlHelper();

                //设置表名
                sh.setTable("NOTIFY_READ");

                //设置Where的条件
                sh.setWhereForInt("NOTIFY_READ_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//NOTIFY_READ_ID

                String sql = sh.getSQL(sh.getDeleteSQL());

                //添加到批处理中
                stmt.addBatch(sql);
            }

            //执行批处理
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
    }

    /**
     * 根据SQL获取通知已读表集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public Collection getNotifyReadColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                NotifyReadVO VO = new NotifyReadVO();

                VO.setNOTIFY_READ_ID(rs.getInt("NOTIFY_READ_ID"));    //NOTIFY_READ_ID
                VO.setSERVICE_ID(rs.getInt("NOTIFY_READ.SERVICE_ID"));    //SERVICE_ID
                VO.setNOTIFY_ID(rs.getInt("NOTIFY_ID"));    //NOTIFY_ID
                VO.setTYPE(rs.getString("NOTIFY_READ.TYPE"));    //读取用户类型
                VO.setTARGET_ID(rs.getInt("TARGET_ID"));    //读取用户id
                VO.setREAD_DATETIME(rs.getTimestamp("READ_DATETIME"));    //读取时间
                VO.setIP(rs.getString("IP"));    //ip
                try {
                    VO.setTARGET_NAME(rs.getString("USER_NAME"));    //ip
                } catch (SQLException e) {

                }
                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得通知已读表集合", e);
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
     * @param NOTIFY_READ_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public NotifyReadVO getNotifyReadByID(int NOTIFY_READ_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("NOTIFY_READ");

        sh.setWhereForInt("NOTIFY_READ_ID", " = ", NOTIFY_READ_ID);//NOTIFY_READ_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getNotifyReadColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (NotifyReadVO) it.next();
        }

        return null;
    }

    /**
     * 根据ID取其VO
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:31:16
     */
    public List<NotifyReadVO> getNotifyReadByNid(int serviceId, int NOTIFY_ID) throws DataAccessException {
        List<NotifyReadVO> list = new ArrayList<>();
        SqlHelper sh = new SqlHelper();
        sh.setSelectColumn("NOTIFY_READ_ID,NOTIFY_READ.SERVICE_ID,NOTIFY_ID,NOTIFY_READ.TYPE,TARGET_ID,READ_DATETIME,IP,USER_NAME,LOGIN_ID");
        sh.setTable("NOTIFY_READ");
        sh.setLeftJoin("USER", "USER.UID =NOTIFY_READ.TARGET_ID and USER.SERVICE_ID=NOTIFY_READ.SERVICE_ID", true);
        sh.setWhereForInt("NOTIFY_ID", " = ", NOTIFY_ID);//NOTIFY_READ_ID
        sh.setWhereForInt("NOTIFY_READ.SERVICE_ID", " = ", serviceId);
        String sql = sh.getSQL(sh.getSelectSQL());
        System.out.println("XQ " + sql);
        Collection coll = getNotifyReadColl(sql);
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            list.add((NotifyReadVO) it.next());
        }
        return list;
    }
}