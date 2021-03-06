package com.xcooper.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.xcooper.comment.vo.TopicVO;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.db.SqlHelper;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.StrUtil;

/**
 * 业务审核流程DAO
 *
 * @author zdk
 *         2016-04-28 11:33:28
 */
public class TopicDAO {

    /**
     * 添加业务审核流程
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public void addTopic(TopicVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("TOPIC");

            sh.setInsertForInt("TOPIC_ID", VO.getTOPIC_ID());//TOPIC_ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setInsertForString("TITLE", VO.getTITLE());//TITLE
            sh.setInsertForString("DESCRIBES", VO.getDESCRIBES());//DESCRIBES
            sh.setInsertForInt("PROJECT_ID", VO.getPROJECT_ID());//PROJECT_ID
            sh.setInsertForInt("CREATE_ID", VO.getCREATE_ID());//CREATE_ID
            sh.setInsertForInt("IS_DONE", VO.getIS_DONE());//IS_DONE
            sh.setInsertForString("REMARK", VO.getREMARK());//REMARK
            sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
            sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME

            sh.insert(ResourceManager.getConnection(), "添加业务审核流程");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改业务审核流程
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public void modifyTopic(TopicVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("TOPIC");

            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForString("TITLE", VO.getTITLE());//TITLE
            sh.setColumnForString("DESCRIBES", VO.getDESCRIBES());//DESCRIBES
            sh.setColumnForInt("IS_DONE", VO.getIS_DONE());//IS_DONE
            sh.setColumnForInt("PROJECT_ID", VO.getPROJECT_ID());//PROJECT_ID
            sh.setColumnForInt("CREATE_ID", VO.getCREATE_ID());//CREATE_ID
            sh.setColumnForString("REMARK", VO.getREMARK());//REMARK
            sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
            sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME

            sh.setWhereForInt("TOPIC_ID", " = ", VO.getTOPIC_ID());//TOPIC_ID

            sh.update(ResourceManager.getConnection(), "修改业务审核流程");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除业务审核流程
     *
     * @param TOPIC_ID
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public void delTopic(int TOPIC_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("TOPIC");

            sh.setWhereForInt("TOPIC_ID", " = ", TOPIC_ID);//TOPIC_ID

            sh.delete(ResourceManager.getConnection(), "删除业务审核流程");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除业务审核流程
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public void delTopic(Collection coll) throws DataAccessException {
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
                sh.setTable("TOPIC");

                //设置Where的条件
                sh.setWhereForInt("TOPIC_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//TOPIC_ID

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
     * 根据SQL获取业务审核流程集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public Collection getTopicColl(String sql) throws DataAccessException {
        System.out.println(sql);

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TopicVO VO = new TopicVO();

                VO.setTOPIC_ID(rs.getInt("TOPIC_ID"));    //TOPIC_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setTITLE(rs.getString("TITLE"));    //TITLE
                VO.setDESCRIBES(rs.getString("DESCRIBES"));    //DESCRIBES
                VO.setIS_DONE(rs.getInt("IS_DONE"));    //IS_DONE
                VO.setREMARK(rs.getString("REMARK"));    //REMARK
                VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));    //ADD_DATETIME
                VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME"));    //UPDATE_DATETIME
                VO.setPROJECT_ID(rs.getInt("PROJECT_ID"));    //UPDATE_DATETIME
                VO.setCREATE_ID(rs.getInt("CREATE_ID"));    //CREATE_ID

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得业务审核流程集合", e);
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
     * @param TOPIC_ID
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public TopicVO getTopicByID(int TOPIC_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("TOPIC");

        sh.setWhereForInt("TOPIC_ID", " = ", TOPIC_ID);//TOPIC_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getTopicColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (TopicVO) it.next();
        }

        return null;
    }

    /**
     * 根据CREATE_ID取其VO
     *
     * @param CREATE_ID
     * @throws DataAccessException
     * @author zdk 2016-04-28 11:33:28
     */
    public TopicVO getTopicByCreateID(int CREATE_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("TOPIC");

        sh.setWhereForInt("CREATE_ID", " = ", CREATE_ID);//CREATE_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getTopicColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (TopicVO) it.next();
        }

        return null;
    }
}