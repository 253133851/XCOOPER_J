package com.xcooper.comment.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.comment.vo.CommentVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 讨论DAO
 *
 * @author zdk
 *         2016-03-28 19:33:56
 */
public class CommentDAO {

    /**
     * 添加讨论
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void addComment(CommentVO VO) throws DataAccessException {

        try {
            if (!checkTile(VO.getCOMMENT_TITLE())) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("COMMENT");
                sh.setInsertForInt("COMMENT_ID", VO.getCOMMENT_ID());//COMMENT_ID
                sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setInsertForInt("COMMENT_MEMBER_ID", VO.getCOMMENT_MEMBER_ID());//评论人
                sh.setInsertForString("COMMENT_TITLE", VO.getCOMMENT_TITLE());//COMMENT_TITLE
                sh.setInsertForString("COMMENT", VO.getCOMMENT());//评论内容
                sh.setInsertForInt("TYPE", VO.getTYPE());//TYPE
                sh.setInsertForString("TARGET_ID", VO.getTARGET_ID());//通知目标
                sh.setInsertForInt("IS_SHOW", VO.getIS_SHOW());//访客是否可见
                sh.setInsertForInt("IS_DONE", VO.getIS_DONE());//是否结束
                sh.setInsertForInt("ORDER_NUM", VO.getORDER_NUM());//排序值
                sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
                sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME
                sh.setInsertForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//END_DATETIME
                sh.insert(ResourceManager.getConnection(), "添加讨论");
            }else{
                throw new DataAccessException("title出错");
            }
        } catch (SQLException e) {
            throw new DataAccessException("出错原因");
        }
    }

    public boolean checkTile(String title) {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setSelectColumn("*");

            sh.setTable("COMMENT");

            sh.setWhereForString("COMMENT_TITLE", " = ", title);//COMMENT_ID

            String sql = sh.getSQL(sh.getSelectSQL());

            System.out.println(sql);

            Collection coll = getCommentColl(sql);
            Iterator it = coll.iterator();
            if (it.hasNext()) {
                return true;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改讨论
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void modifyComment(CommentVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("COMMENT");

            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForInt("COMMENT_MEMBER_ID", VO.getCOMMENT_MEMBER_ID());//评论人
            sh.setColumnForString("COMMENT_TITLE", VO.getCOMMENT_TITLE());//COMMENT_TITLE
            sh.setColumnForString("COMMENT", VO.getCOMMENT());//评论内容
            sh.setColumnForInt("TYPE", VO.getTYPE());//TYPE
            sh.setColumnForString("TARGET_ID", VO.getTARGET_ID());//通知目标
            sh.setColumnForInt("IS_SHOW", VO.getIS_SHOW());//访客是否可见
            sh.setColumnForInt("IS_DONE", VO.getIS_DONE());//是否结束
            sh.setColumnForInt("ORDER_NUM", VO.getORDER_NUM());//排序值
            sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
            sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME
            sh.setColumnForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//END_DATETIME

            sh.setWhereForInt("COMMENT_ID", " = ", VO.getCOMMENT_ID());//COMMENT_ID

            sh.update(ResourceManager.getConnection(), "修改讨论");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除讨论
     *
     * @param COMMENT_ID
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void delComment(int COMMENT_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("COMMENT");

            sh.setWhereForInt("COMMENT_ID", " = ", COMMENT_ID);//COMMENT_ID

            sh.delete(ResourceManager.getConnection(), "删除讨论");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除讨论
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void delComment(Collection coll) throws DataAccessException {
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
                sh.setTable("COMMENT");

                //设置Where的条件
                sh.setWhereForInt("COMMENT_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//COMMENT_ID

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
     * 根据SQL获取讨论集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public Collection getCommentColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CommentVO VO = new CommentVO();

                VO.setCOMMENT_ID(rs.getInt("COMMENT_ID"));    //COMMENT_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setCOMMENT_MEMBER_ID(rs.getInt("COMMENT_MEMBER_ID"));    //评论人
                VO.setCOMMENT_TITLE(rs.getString("COMMENT_TITLE"));    //COMMENT_TITLE
                VO.setCOMMENT(rs.getString("COMMENT"));    //评论内容
                VO.setTYPE(rs.getInt("TYPE"));    //TYPE
                VO.setTARGET_ID(rs.getString("TARGET_ID"));    //通知目标
                VO.setIS_SHOW(rs.getInt("IS_SHOW"));    //访客是否可见
                VO.setIS_DONE(rs.getInt("IS_DONE"));    //是否结束
                VO.setORDER_NUM(rs.getInt("ORDER_NUM"));    //排序值
                VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));    //ADD_DATETIME
                VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME"));    //UPDATE_DATETIME
                VO.setEND_DATETIME(rs.getTimestamp("END_DATETIME"));    //END_DATETIME
                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得讨论集合", e);
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
     * @param COMMENT_ID
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public CommentVO getCommentByID(int COMMENT_ID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("COMMENT");

        sh.setWhereForInt("COMMENT_ID", " = ", COMMENT_ID);//COMMENT_ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getCommentColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (CommentVO) it.next();
        }
        return null;
    }

    /**
     * 根据ID取其VO
     *
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public CommentVO getComment() throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("COMMENT");

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getCommentColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (CommentVO) it.next();
        }
        return null;
    }

}