package com.jiaorder.sys.memberlevel.dao;

import com.jiaorder.sys.memberlevel.vo.MemberLevelVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户级别
 */
public class MemberLevelDAO {

    /**
     * 新增客户级别
     *
     * @param VO
     */
    public boolean addMemberLevel(int serviceId, MemberLevelVO VO) {
        try {
            if (checkRepeatMemberLevel(serviceId, VO.getLEVEL_NAME(),VO.getMEMBER_LEVEL_ID())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("MEMBER_LEVEL");
                sqlHelper.setInsertForInt("MEMBER_LEVEL_ID", VO.getMEMBER_LEVEL_ID());
                sqlHelper.setInsertForInt("ORDER_NUM", VO.getORDER_NUM());//排序值
                sqlHelper.setInsertForInt("MEMBER_COUNT", VO.getMEMBER_COUNT());//客户数量
                sqlHelper.setInsertForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否为默认
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//公司名
                sqlHelper.setInsertForString("LEVEL_NAME", VO.getLEVEL_NAME());//级别名称
                sqlHelper.setInsertForString("PRICE_OFF", VO.getPRICE_OFF());//折扣
                sqlHelper.insert(ResourceManager.getConnection(), "新增客户级别");
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
     * 删除客户级别
     *
     * @param ids id之间用逗号分隔开
     */
    public boolean delMemberLevel(int serviceId, String ids) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("MEMBER_LEVEL");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("MEMBER_LEVEL_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除客户级别");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            ResourceManager.close(connection);
            ResourceManager.close(statement);
        }
        return false;
    }

    /**
     * 更新客户级别
     *
     * @param VO
     */
    public boolean updateMemberLevel(int serviceId, MemberLevelVO VO) {
        try {
            if (checkRepeatMemberLevel(serviceId, VO.getLEVEL_NAME(), VO.getMEMBER_LEVEL_ID())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("MEMBER_LEVEL");
                sqlHelper.setColumnForInt("MEMBER_LEVEL_ID", VO.getMEMBER_LEVEL_ID());
                sqlHelper.setColumnForInt("ORDER_NUM", VO.getORDER_NUM());//排序值
                sqlHelper.setColumnForInt("MEMBER_COUNT", VO.getMEMBER_COUNT());//客户数量
                sqlHelper.setColumnForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否默认
                sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//公司名
                sqlHelper.setColumnForString("LEVEL_NAME", VO.getLEVEL_NAME());//级别名称
                sqlHelper.setColumnForString("PRICE_OFF", VO.getPRICE_OFF());//折扣
                sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
                sqlHelper.setWhereForInt("MEMBER_LEVEL_ID", " = ", VO.getMEMBER_LEVEL_ID());
                sqlHelper.update(ResourceManager.getConnection(), "修改客户级别");
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断重复数据
     *
     * @param serviceId
     * @param MemberLevel
     * @return
     */
    public boolean checkRepeatMemberLevel(int serviceId, String MemberLevel,int levelid ) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("MEMBER_LEVEL");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("MEMBER_LEVEL_ID", " != ", levelid);
            sqlHelper.setWhereForString("LEVEL_NAME", " = ", MemberLevel);//级别名称
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<MemberLevelVO> list = getMemberLevelColl(sql);
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
     * 用id查询客户级别
     */
    public MemberLevelVO queryMemberLevelById(int serviceId, int MemberLevelId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("MEMBER_LEVEL");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("MEMBER_LEVEL_ID", " = ", MemberLevelId);
            sqlHelper.setORDER("ORDER_NUM DESC");//排序值倒序
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<MemberLevelVO> list = getMemberLevelColl(sql);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new MemberLevelVO();
    }

    /**
     * 查询所有客户级别
     */
    public List<MemberLevelVO> queryMemberLevel(int serviceId) {
        List<MemberLevelVO> MemberLevelVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("MEMBER_LEVEL");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setORDER("ORDER_NUM DESC");//排序值倒序
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            MemberLevelVOs = getMemberLevelColl(sql);
            if (MemberLevelVOs.size() > 0) {
                return MemberLevelVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据SQL获取客户级别集合
     *
     * @param sql
     */
    public List<MemberLevelVO> getMemberLevelColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MemberLevelVO VO = new MemberLevelVO();
                VO.setMEMBER_LEVEL_ID(rs.getInt("MEMBER_LEVEL_ID"));
                VO.setORDER_NUM(rs.getInt("ORDER_NUM"));//排序值
                VO.setMEMBER_COUNT(rs.getInt("MEMBER_COUNT"));//客户数量
                VO.setIS_DEFAULT(rs.getInt("IS_DEFAULT"));//是否默认
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));//公司名
                VO.setLEVEL_NAME(rs.getString("LEVEL_NAME"));//级别名称
                VO.setPRICE_OFF(rs.getString("PRICE_OFF"));//折扣
                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
        return resultList;
    }

}
