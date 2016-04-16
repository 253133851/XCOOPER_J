package com.jiaorder.member.notify.dao;

import com.jiaorder.member.notify.vo.NotifyUserVO;
import com.pabula.common.db.SqlHelper;
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
 * 通知-用户表DAO
 *
 * @author zdk
 *         2016-03-21 15:29:43
 */
public class NotifyUserDAO {

    /**
     * 添加通知-用户表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public void addNotifyUser(NotifyUserVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY_USER");

            sh.setInsertForInt("NOTIFY_USER_ID", VO.getNOTIFY_USER_ID());//NOTIFY_USER_ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setInsertForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
            sh.setInsertForString("TYPE", VO.getTYPE());//通知用户类型
            sh.setInsertForInt("TARGET_ID", VO.getTARGET_ID());//通知目标id

            sh.insert(ResourceManager.getConnection(), "添加通知-用户表");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改通知-用户表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public void modifyNotifyUser(NotifyUserVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY_USER");

            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
            sh.setColumnForString("TYPE", VO.getTYPE());//通知用户类型
            sh.setColumnForInt("TARGET_ID", VO.getTARGET_ID());//通知目标id

            sh.setWhereForInt("NOTIFY_USER_ID", " = ", VO.getNOTIFY_USER_ID());//NOTIFY_USER_ID

            sh.update(ResourceManager.getConnection(), "修改通知-用户表");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除通知-用户表
     *
     * @param NOTIFY_USER_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public void delNotifyUser(int NOTIFY_USER_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY_USER");

            sh.setWhereForInt("NOTIFY_USER_ID", " = ", NOTIFY_USER_ID);//NOTIFY_USER_ID

            sh.delete(ResourceManager.getConnection(), "删除通知-用户表");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量添加通知-用户表
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public void addNotifyUserList(List<NotifyUserVO> list) throws DataAccessException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            for (int i = 0; i < list.size(); i++) {
                NotifyUserVO VO = list.get(i);
                SqlHelper sh = new SqlHelper();
                sh.setTable("NOTIFY_USER");
                sh.setInsertForInt("NOTIFY_USER_ID", VO.getNOTIFY_USER_ID());//NOTIFY_USER_ID
                sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setInsertForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
                sh.setInsertForString("TYPE", VO.getTYPE());//通知用户类型
                sh.setInsertForInt("TARGET_ID", VO.getTARGET_ID());//通知目标id
                String sql = sh.getSQL(sh.getInsertSQL());
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
     * 批量删除通知-用户表
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public void delNotifyUser(Collection coll) throws DataAccessException {
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
                sh.setTable("NOTIFY_USER");

                //设置Where的条件
                sh.setWhereForInt("NOTIFY_USER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//NOTIFY_USER_ID

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
     * 根据SQL获取通知-用户表集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public Collection getNotifyUserColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                NotifyUserVO VO = new NotifyUserVO();

                VO.setNOTIFY_USER_ID(rs.getInt("NOTIFY_USER_ID"));    //NOTIFY_USER_ID
                VO.setSERVICE_ID(rs.getInt("NOTIFY_USER.SERVICE_ID"));    //SERVICE_ID
                VO.setNOTIFY_ID(rs.getInt("NOTIFY_ID"));    //NOTIFY_ID
                VO.setTYPE(rs.getString("NOTIFY_USER.TYPE"));    //通知用户类型
                VO.setTARGET_ID(rs.getInt("TARGET_ID"));    //通知目标id
                VO.setTARGET_NAME(rs.getString("USER_NAME"));    //ip
                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得通知-用户表集合", e);
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
     * @param NOTIFY_USER_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public NotifyUserVO getNotifyUserByID(int NOTIFY_USER_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("NOTIFY_USER");

        sh.setWhereForInt("NOTIFY_USER_ID", " = ", NOTIFY_USER_ID);//NOTIFY_USER_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getNotifyUserColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (NotifyUserVO) it.next();
        }

        return null;
    }


    /**
     * 根据ID取其VO
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public List<NotifyUserVO> getNotifyUserByNID(int serviceId, int NOTIFY_ID) throws DataAccessException {

        List<NotifyUserVO> notifyUserVOs = new ArrayList<>();

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("NOTIFY_USER_ID,NOTIFY_USER.SERVICE_ID,NOTIFY_ID,NOTIFY_USER.TYPE,TARGET_ID,USER_NAME,LOGIN_ID");

        sh.setTable("NOTIFY_USER");

        sh.setLeftJoin("USER", "USER.UID =NOTIFY_USER.TARGET_ID and USER.SERVICE_ID=NOTIFY_USER.SERVICE_ID", true);

        sh.setWhereForInt("NOTIFY_ID", " = ", NOTIFY_ID);//NOTIFY_USER_ID

        sh.setWhereForInt("NOTIFY_USER.SERVICE_ID", " = ", serviceId);//NOTIFY_USER_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getNotifyUserColl(sql);
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            notifyUserVOs.add((NotifyUserVO) it.next());
        }
        return notifyUserVOs;
    }

    /**
     * 根据UID判断通知权限
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:29:43
     */
    public boolean checkReadableByUid(int serviceId, int userId, int notifyId) throws DataAccessException {

        SqlHelper sh = new SqlHelper();


        sh.setSelectColumn("NOTIFY_USER_ID,NOTIFY_USER.SERVICE_ID,NOTIFY_ID,NOTIFY_USER.TYPE,TARGET_ID,USER_NAME,LOGIN_ID");

        sh.setTable("NOTIFY_USER");

        sh.setLeftJoin("USER", "USER.UID =NOTIFY_USER.TARGET_ID and USER.SERVICE_ID=NOTIFY_USER.SERVICE_ID", true);

        sh.setWhereForInt("NOTIFY_USER.SERVICE_ID", " = ", serviceId);//NOTIFY_USER_ID

        sh.setWhereForInt("NOTIFY_ID", " = ", notifyId);//NOTIFY_USER_ID


        sh.setWhereForInt("TARGET_ID", " = ", userId);//NOTIFY_USER_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getNotifyUserColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return true;
        }
        return false;
    }
}