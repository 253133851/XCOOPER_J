package com.jiaorder.shop.unit.dao;

import com.jiaorder.shop.unit.vo.UnitVO;
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
 * 单位dao
 */
public class UnitDAO {

    /**
     * 新增单位
     *
     * @param VO
     */
    public boolean addUnit(int serviceId, UnitVO VO) {
        try {
            if (checkRepeatUnit(serviceId, VO.getUNIT())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_UNIT");
                sqlHelper.setInsertForInt("UNIT_ID", VO.getUNIT_ID());
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setInsertForString("UNIT", VO.getUNIT());//单位名
                sqlHelper.insert(ResourceManager.getConnection(), "新增单位");
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
     * 删除单位
     *
     * @param ids id之间用逗号分隔开
     */
    public boolean delUnit(int serviceId, String ids) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SHOP_UNIT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("UNIT_ID", "=", ids, true);//要删除的单位id集合
            sqlHelper.delete(connection, "删除单位");
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
     * 判断重复数据
     *
     * @param serviceId
     * @param unit
     * @return
     */
    public boolean checkRepeatUnit(int serviceId, String unit) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_UNIT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForString("UNIT", " = ", unit);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<UnitVO> list = getUnitColl(sql);
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
     * 用id查询单位
     */
    public UnitVO queryUnitById(int unitId, int serviceId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_UNIT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("UNIT_ID", " = ", unitId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<UnitVO> list = getUnitColl(sql);
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new UnitVO();
    }

    /**
     * 查询所有单位
     */
    public List<UnitVO> queryUnit(int serviceId) {
        List<UnitVO> unitVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_UNIT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            unitVOs = getUnitColl(sql);
            if (unitVOs.size() > 0) {
                return unitVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据SQL获取商品单位集合
     *
     * @param sql
     */
    public List<UnitVO> getUnitColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UnitVO VO = new UnitVO();
                VO.setUNIT_ID(rs.getInt("UNIT_ID"));//单位id
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));//公司名
                VO.setUNIT(rs.getString("UNIT"));//单位名
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
