package com.jiaorder.member.guestbook.dao;

import com.jiaorder.member.guestbook.vo.GuestbookAnswerVO;
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
 * 客户反馈回复DAO
 *
 * @author zdk
 *         2016-03-21 15:21:06
 */
public class GuestbookAnswerDAO {

    /**
     * 添加客户反馈回复
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public void addGuestbookAnswer(GuestbookAnswerVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("GUESTBOOK_ANSWER");

            sh.setInsertForInt("GUESTBOOK_ANSWER_ID", VO.getGUESTBOOK_ANSWER_ID());//GUESTBOOK_ANSWER_ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setInsertForInt("GUESTBOOK_ID", VO.getGUESTBOOK_ID());//GUESTBOOK_ID
            sh.setInsertForString("CONTENT", VO.getCONTENT());//回复内容
            sh.setInsertForInt("USER_ID", VO.getUSER_ID());//回复人
            sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//添加时间
            sh.setInsertForDatetime("ANSWER_DATETIME", DateUtil.format(VO.getANSWER_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//回复时间
            sh.setInsertForInt("IS_DEL", VO.getIS_DEL());//是否删除
            sh.setInsertForString("IP", VO.getIP());//ip
            System.out.println(sh.getSQL(sh.getInsertSQL()));
            sh.insert(ResourceManager.getConnection(), "添加客户反馈回复");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改客户反馈回复
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public void modifyGuestbookAnswer(GuestbookAnswerVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("GUESTBOOK_ANSWER");

            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForInt("GUESTBOOK_ID", VO.getGUESTBOOK_ID());//GUESTBOOK_ID
            sh.setColumnForString("CONTENT", VO.getCONTENT());//回复内容
            sh.setColumnForInt("USER_ID", VO.getUSER_ID());//回复人
            sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//添加时间
            sh.setColumnForDatetime("ANSWER_DATETIME", DateUtil.format(VO.getANSWER_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//回复时间
            sh.setColumnForInt("IS_DEL", VO.getIS_DEL());//是否删除
            sh.setColumnForString("IP", VO.getIP());//ip

            sh.setWhereForInt("GUESTBOOK_ANSWER_ID", " = ", VO.getGUESTBOOK_ANSWER_ID());//GUESTBOOK_ANSWER_ID

            sh.update(ResourceManager.getConnection(), "修改客户反馈回复");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除客户反馈回复
     *
     * @param GUESTBOOK_ANSWER_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public void delGuestbookAnswer(int GUESTBOOK_ANSWER_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("GUESTBOOK_ANSWER");

            sh.setWhereForInt("GUESTBOOK_ANSWER_ID", " = ", GUESTBOOK_ANSWER_ID);//GUESTBOOK_ANSWER_ID

            sh.delete(ResourceManager.getConnection(), "删除客户反馈回复");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除客户反馈回复
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public void delGuestbookAnswer(Collection coll) throws DataAccessException {
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
                sh.setTable("GUESTBOOK_ANSWER");

                //设置Where的条件
                sh.setWhereForInt("GUESTBOOK_ANSWER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//GUESTBOOK_ANSWER_ID

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
     * 根据SQL获取客户反馈回复集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public Collection getGuestbookAnswerColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                GuestbookAnswerVO VO = new GuestbookAnswerVO();

                VO.setGUESTBOOK_ANSWER_ID(rs.getInt("GUESTBOOK_ANSWER_ID"));    //GUESTBOOK_ANSWER_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setGUESTBOOK_ID(rs.getInt("GUESTBOOK_ID"));    //GUESTBOOK_ID
                VO.setCONTENT(rs.getString("CONTENT"));    //回复内容
                VO.setUSER_ID(rs.getInt("USER_ID"));    //回复人
                VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));    //添加时间
                VO.setANSWER_DATETIME(rs.getTimestamp("ANSWER_DATETIME"));    //回复时间
                VO.setIS_DEL(rs.getInt("IS_DEL"));    //是否删除
                VO.setIP(rs.getString("IP"));    //ip

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得客户反馈回复集合", e);
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
        return resultList;
    }

    /**
     * 根据ID取其VOs
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:21:06
     */
    public List<GuestbookAnswerVO> getGuestbookAnswerByID(String ids) throws DataAccessException {
        List<GuestbookAnswerVO> guestbookAnswerVOs=new ArrayList<>();
        SqlHelper sh = new SqlHelper();
        sh.setSelectColumn("*");
        sh.setTable("GUESTBOOK_ANSWER");
        sh.setOrGroupForInt("GUESTBOOK_ID", " = ", ids, true);//GUESTBOOK_ANSWER_ID
        String sql = sh.getSQL(sh.getSelectSQL());
        Collection coll = getGuestbookAnswerColl(sql);
        System.out.println(sql);
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            guestbookAnswerVOs.add((GuestbookAnswerVO) it.next());
        }
        return guestbookAnswerVOs;
    }
}