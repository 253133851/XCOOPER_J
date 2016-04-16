package com.jiaorder.shop.brand.dao;

/**
 * Created by hh on 2016.2.25.
 */

import com.jiaorder.shop.brand.vo.BrandVO;
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
 * 品牌dao
 */
public class BrandDAO {

    /**
     * 新增品牌
     *
     * @param VO
     */
    public boolean addBrand(int serviceId, BrandVO VO) {
        try {
            if (checkRepeatBrand(serviceId, VO.getBRAND())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_BRAND");
                sqlHelper.setInsertForInt("BRAND_ID", VO.getBRAND_ID());
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setInsertForString("BRAND", VO.getBRAND());//品牌名
                sqlHelper.insert(ResourceManager.getConnection(), "新增品牌");
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
     * 判断重复数据
     *
     * @param serviceId
     * @param Brand
     * @return
     */
    public boolean checkRepeatBrand(int serviceId, String Brand) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_Brand");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForString("Brand", " = ", Brand);//品牌名相同
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<BrandVO> list = getBrandColl(sql);
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
     * 删除品牌
     *
     * @param ids id之间用逗号分隔开
     */
    public boolean delBrand(int serviceId, String ids) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SHOP_BRAND");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("BRAND_ID", "=", ids, true); //要删除的品牌id集合
            sqlHelper.delete(connection, "删除品牌");
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
     * 更新品牌
     *
     * @param VO
     */
    public boolean updateBrand(int serviceId, BrandVO VO) {
        try {
            if (checkRepeatBrand(serviceId, VO.getBRAND())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_BRAND");
                sqlHelper.setColumnForInt("BRAND_ID", VO.getBRAND_ID());
                sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setColumnForString("BRAND", VO.getBRAND());
                sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
                sqlHelper.setWhereForInt("BRAND_ID", " = ", VO.getBRAND_ID());
                sqlHelper.update(ResourceManager.getConnection(), "修改商品品牌");
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
     * 用id查询品牌
     */
    public BrandVO queryBrandById(int serviceId, int brandId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_BRAND");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("BRAND_ID", " = ", brandId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<BrandVO> list = getBrandColl(sql);
            if (list.size() > 0) {
                return list.get(0);
            }
            return new BrandVO();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new BrandVO();
    }

    /**
     * 查询所有品牌
     */
    public List<BrandVO> queryBrand(int serviceId) {
        List<BrandVO> brandVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_BRAND");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            brandVOs = getBrandColl(sql);
            if (brandVOs.size() > 0) {
                return brandVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据SQL获取商品品牌集合
     *
     * @param sql
     */
    public List<BrandVO> getBrandColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BrandVO VO = new BrandVO();
                VO.setBRAND_ID(rs.getInt("BRAND_ID"));//品牌ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setBRAND(rs.getString("BRAND"));//品牌名称
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
