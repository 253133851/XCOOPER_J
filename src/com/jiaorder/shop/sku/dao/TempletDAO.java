package com.jiaorder.shop.sku.dao;

import com.jiaorder.shop.sku.vo.TempletVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2016.2.25.
 */
public class TempletDAO {

    /**
     * 新增
     *
     * @param VO
     */
    public void addTemplet(TempletVO VO) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SKU_TEMPLET");
            sqlHelper.setInsertForInt("SKU_TEMPLET_ID", VO.getSKU_TEMPLET_ID());
            sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
            sqlHelper.setInsertForString("SKU_NAME1", VO.getSKU_NAME1());
            sqlHelper.setInsertForString("SKU_NAME2", VO.getSKU_NAME2());
            sqlHelper.setInsertForString("SKU_NAME3", VO.getSKU_NAME3());
            sqlHelper.setInsertForString("SKU_CONTENT1", VO.getSKU_CONTENT1());
            sqlHelper.setInsertForString("SKU_CONTENT2", VO.getSKU_CONTENT2());
            sqlHelper.setInsertForString("SKU_CONTENT3", VO.getSKU_CONTENT3());
            sqlHelper.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
            sqlHelper.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
            sqlHelper.insert(ResourceManager.getConnection(), "新增");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     *
     * @param ids id之间用逗号分隔开
     */
    public void delTemplet(String ids, int serviceId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SKU_TEMPLET");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("SKU_TEMPLET_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            ResourceManager.close(connection);
            ResourceManager.close(statement);
        }
    }


    /**
     * 更新
     *
     * @param VO
     */
    public void updateTemplet(TempletVO VO, int serviceId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SKU_TEMPLET");
            sqlHelper.setColumnForInt("SKU_TEMPLET_ID", VO.getSKU_TEMPLET_ID());
            sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
            sqlHelper.setColumnForString("SKU_NAME1", VO.getSKU_NAME1());
            sqlHelper.setColumnForString("SKU_NAME2", VO.getSKU_NAME2());
            sqlHelper.setColumnForString("SKU_NAME3", VO.getSKU_NAME3());
            sqlHelper.setColumnForString("SKU_CONTENT1", VO.getSKU_CONTENT1());
            sqlHelper.setColumnForString("SKU_CONTENT2", VO.getSKU_CONTENT2());
            sqlHelper.setColumnForString("SKU_CONTENT3", VO.getSKU_CONTENT3());
            sqlHelper.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);
            sqlHelper.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("SKU_TEMPLET_ID", " = ", VO.getSKU_TEMPLET_ID());
            sqlHelper.update(ResourceManager.getConnection(), "修改");
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用id查询
     */
    public TempletVO queryTempletById(int TempletId, int serviceId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SKU_TEMPLET");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("SKU_TEMPLET_ID", " = ", TempletId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<TempletVO> list = getTempletColl(sql);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有
     */
    public List<TempletVO> queryTemplet(int serviceId) {
        List<TempletVO> templetVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SKU_TEMPLET");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            templetVOs = getTempletColl(sql);
            if (templetVOs.size() > 0) {
                return templetVOs;
            }
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据SQL获取集合
     *
     * @param sql
     */
    public List<TempletVO> getTempletColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TempletVO VO = new TempletVO();
                VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));
                VO.setSKU_CONTENT1(rs.getString("SKU_CONTENT1"));
                VO.setSKU_CONTENT2(rs.getString("SKU_CONTENT2"));
                VO.setSKU_CONTENT3(rs.getString("SKU_CONTENT3"));
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setSKU_NAME1(rs.getString("SKU_NAME1"));
                VO.setSKU_NAME2(rs.getString("SKU_NAME2"));
                VO.setSKU_NAME3(rs.getString("SKU_NAME3"));
                VO.setSKU_TEMPLET_ID(rs.getInt("SKU_TEMPLET_ID"));
                VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME"));
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
