package com.jiaorder.member.notify.dao;

import com.jiaorder.member.notify.vo.NotifyVO;
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
 * 通知公告DAO
 *
 * @author cwq
 *         2016-03-21 15:02:59
 */
public class NotifyDAO {

    /**
     * 添加通知公告
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public boolean addNotify(NotifyVO VO) {
        try {
            SqlHelper sh = new SqlHelper();
            sh.setTable("NOTIFY");
            sh.setInsertForInt("NOTIFY_ID", VO.getNOTIFY_ID());//NOTIFY_ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setInsertForInt("CLASS_ID", VO.getCLASS_ID());//CLASS_ID
            sh.setInsertForString("TITLE", VO.getTITLE());//通知标题
            sh.setInsertForString("INTRO", VO.getINTRO());//通知富文本
            sh.setInsertForInt("READ_NUM", VO.getREAD_NUM());//已读人数
            sh.setInsertForString("NOTIFY_SHOW", VO.getNOTIFY_SHOW());//可见性状态
            sh.setInsertForString("TYPE", VO.getTYPE());//类型
            sh.setInsertForString("AD_IMG", VO.getAD_IMG());//广告图片
            sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//发布时间
            sh.insert(ResourceManager.getConnection(), "添加通知公告");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改通知公告
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void modifyNotify(NotifyVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY");
            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForInt("CLASS_ID", VO.getCLASS_ID());//CLASS_ID
            sh.setColumnForString("TITLE", VO.getTITLE());//通知标题
            sh.setColumnForString("INTRO", VO.getINTRO());//通知富文本
            sh.setColumnForInt("READ_NUM", VO.getREAD_NUM());//已读人数
            sh.setColumnForString("NOTIFY_SHOW", VO.getNOTIFY_SHOW());//可见性状态
            sh.setColumnForString("TYPE", VO.getTYPE());//类型
            sh.setColumnForString("AD_IMG", VO.getAD_IMG());//广告图片
            sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//发布时间
            sh.setWhereForInt("NOTIFY_ID", " = ", VO.getNOTIFY_ID());//NOTIFY_ID

            sh.update(ResourceManager.getConnection(), "修改通知公告");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改通知公告Id
     *
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public boolean modifyNotifyId(int serviceId, int oldId, int newId) {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY");
            sh.setColumnForInt("CLASS_ID", newId);//CLASS_ID
            sh.setWhereForInt("CLASS_ID", " = ", oldId);//NOTIFY_ID
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//NOTIFY_ID
            sh.update(ResourceManager.getConnection(), "修改通知公告的分类");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除通知公告
     *
     * @param NOTIFY_ID
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void delNotify(int NOTIFY_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("NOTIFY");

            sh.setWhereForInt("NOTIFY_ID", " = ", NOTIFY_ID);//NOTIFY_ID

            sh.delete(ResourceManager.getConnection(), "删除通知公告");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除通知公告
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void delNotify(Collection coll) throws DataAccessException {
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
                sh.setTable("NOTIFY");

                //设置Where的条件
                sh.setWhereForInt("NOTIFY_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//NOTIFY_ID

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
     * 根据SQL获取通知公告集合
     *
     * @param sql
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public List<NotifyVO> getNotifyColl(String sql) throws DataAccessException {

        List<NotifyVO> resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                NotifyVO VO = new NotifyVO();

                VO.setNOTIFY_ID(rs.getInt("NOTIFY_ID"));    //NOTIFY_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setCLASS_ID(rs.getInt("CLASS_ID"));    //CLASS_ID
                VO.setTITLE(rs.getString("TITLE"));    //通知标题
                VO.setINTRO(rs.getString("INTRO"));    //通知富文本
                VO.setREAD_NUM(rs.getInt("READ_NUM"));    //已读人数
                VO.setNOTIFY_SHOW(rs.getString("NOTIFY_SHOW"));    //可见性状态
                VO.setTYPE(rs.getString("TYPE"));    //类型
                VO.setCLASS_NAME(rs.getString("NAME"));    //类型
                VO.setAD_IMG(rs.getString("AD_IMG"));    //广告图片
                VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));    //发布时间

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得通知公告集合", e);
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
     * @param NOTIFY_ID
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public NotifyVO getNotifyByID(int serviceId,int NOTIFY_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();
        sh.setSelectColumn("*");
        sh.setTable("NOTIFY");
        sh.setLeftJoin("NOTIFY_CLASS", "NOTIFY.CLASS_ID =NOTIFY_CLASS.NOTIFY_CLASS_ID and NOTIFY.SERVICE_ID=NOTIFY_CLASS.SERVICE_ID", true);
        sh.setWhereForInt("NOTIFY_ID", " = ", NOTIFY_ID);//NOTIFY_ID
        sh.setWhereForInt("NOTIFY.SERVICE_ID", " = ", serviceId);
        sh.setORDER("ORDER_NUM DESC");
        String sql = sh.getSQL(sh.getSelectSQL());
        Collection coll = getNotifyColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (NotifyVO) it.next();
        }

        return new NotifyVO();
    }


    /**
     * 查询所有通知
     */
    public List<NotifyVO> queryNotify(int serviceId, NotifyVO filterMember) {
        List<NotifyVO> notifyVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("NOTIFY");
            sqlHelper.setSelectColumn("*");
            sqlHelper.setLeftJoin("NOTIFY_CLASS", "NOTIFY.CLASS_ID =NOTIFY_CLASS.NOTIFY_CLASS_ID and NOTIFY.SERVICE_ID=NOTIFY_CLASS.SERVICE_ID", true);
            sqlHelper.setWhereForInt("NOTIFY.SERVICE_ID", " = ", serviceId);
            if (filterMember != null) {
                sqlHelper = filterSqlHelper(sqlHelper, filterMember, true);
            }
            sqlHelper.setORDER("ORDER_NUM DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            notifyVOs = getNotifyColl(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return notifyVOs;
    }

    /**
     * 过滤条件
     *
     * @param sqlHelper
     * @param filterMember
     * @return
     * @throws DataAccessException
     */
    public SqlHelper filterSqlHelper(SqlHelper sqlHelper, NotifyVO filterMember, boolean withLimit) throws DataAccessException {
        if (filterMember.getCLASS_ID() != -100) {
            sqlHelper.setWhereForInt("NOTIFY.CLASS_ID", " = ", filterMember.getCLASS_ID());
        }
        if (null != filterMember.getFILTER_TIME() && !("").equals(filterMember.getFILTER_TIME())) {
            sqlHelper.setWhereForString("NOTIFY.ADD_DATETIME", " > ", filterMember.getFILTER_TIME());
        }
        if (null!=filterMember.getNOTIFY_SHOW()&&!("").equals(filterMember.getNOTIFY_SHOW())){
            sqlHelper.setOrGroupForString("NOTIFY_SHOW","=",new String[]{filterMember.getNOTIFY_SHOW(),"AIM_PEOPLE","ALL"},true);
        }
//        if (withLimit) {
//            sqlHelper.setLIMIT((filterMember.getPageIndex() - 1) * filterMember.getPageSize(), filterMember.getPageSize());
//        }
        return sqlHelper;
    }


    /**
     * 查询符合目标条件下的总条数
     */
    public List<NotifyVO> queryNotify_count(int serviceId, NotifyVO filterMember) {
        List<NotifyVO> notifyVOs=new ArrayList<>();
        try {

            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("NOTIFY");
            sqlHelper.setSelectColumn("NOTIFY_ID,NOTIFY_SHOW");
            sqlHelper.setLeftJoin("NOTIFY_CLASS", "NOTIFY.CLASS_ID =NOTIFY_CLASS.NOTIFY_CLASS_ID and NOTIFY.SERVICE_ID=NOTIFY_CLASS.SERVICE_ID", true);
            sqlHelper.setWhereForInt("NOTIFY.SERVICE_ID", " = ", serviceId);
            if (filterMember != null) {
                sqlHelper = filterSqlHelper(sqlHelper, filterMember, false);
            }
            sqlHelper.setORDER("ORDER_NUM DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = ResourceManager.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    NotifyVO notifyVO=new NotifyVO();
                    notifyVO.setNOTIFY_ID(rs.getInt("NOTIFY_ID"));
                    notifyVO.setNOTIFY_SHOW(rs.getString("NOTIFY_SHOW"));
                    notifyVOs.add(notifyVO);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ResourceManager.close(rs);
                ResourceManager.close(stmt);
                ResourceManager.close(conn);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return notifyVOs;
    }


}