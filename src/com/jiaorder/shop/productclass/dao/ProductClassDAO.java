package com.jiaorder.shop.productclass.dao;

import com.jiaorder.shop.productclass.vo.ProductClassVO;
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
 * 分类dao
 */
public class ProductClassDAO {

    /**
     * 新增分类
     *
     * @param VO
     */
    public boolean addProductClass(ProductClassVO VO) {
        try {
            if (checkRepeatProductClass(VO)) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_PRODUCT_CLASS");
                sqlHelper.setInsertForInt("CLASS_ID", VO.getCLASS_ID());
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setInsertForInt("CLASS_ORDER_NUM", VO.getCLASS_ORDER_NUM());//p排序值
                sqlHelper.setInsertForInt("PARENT_CLASS_ID", VO.getPARENT_CLASS_ID());//父分类ID
                sqlHelper.setInsertForInt("IS_DEL", VO.getIS_DEL());//是否被删除
                sqlHelper.setInsertForString("CLASS_NAME", VO.getCLASS_NAME());//分类名
                sqlHelper.setInsertForString("CLASS_PATH", VO.getCLASS_PATH());//分类路径
                sqlHelper.setInsertForString("CLASS_ICON", VO.getCLASS_ICON());//分类图标
                sqlHelper.insert(ResourceManager.getConnection(), "新增分类");
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
     * 删除分类
     *
     * @param ids id之间用逗号分隔开
     */
    public void delProductClass(String ids, int serviceId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SHOP_PRODUCT_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("CLASS_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除分类");
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
     * 判断重复数据 (同层级)
     *
     * @return
     */
    public boolean checkRepeatProductClass(ProductClassVO VO) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_PRODUCT_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());
            sqlHelper.setWhereForInt("CLASS_ID", " != ", VO.getCLASS_ID());
            sqlHelper.setWhereForInt("PARENT_CLASS_ID", " = ", VO.getPARENT_CLASS_ID());//父分类名称
            sqlHelper.setWhereForString("CLASS_NAME", " = ", VO.getCLASS_NAME());//分类名称
            sqlHelper.setWhereForInt("IS_DEL", " != ", ProductClassVO.HAS_DELETED);//未被删除的
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<ProductClassVO> list = getProductClassColl(sql);
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
     * 更新分类
     *
     * @param VO
     */
    public boolean updateProductClass(int serviceId, ProductClassVO VO) {
        try {
            if (checkRepeatProductClass(VO)) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_PRODUCT_CLASS");
                sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setColumnForInt("CLASS_ORDER_NUM", VO.getCLASS_ORDER_NUM());//排序值
                sqlHelper.setColumnForInt("PARENT_CLASS_ID", VO.getPARENT_CLASS_ID());//父分类ID
                sqlHelper.setColumnForInt("IS_DEL", VO.getIS_DEL());//删除状态
                sqlHelper.setColumnForString("CLASS_NAME", VO.getCLASS_NAME());//分类名称
                sqlHelper.setColumnForString("CLASS_PATH", VO.getCLASS_PATH());//分类路径
                sqlHelper.setColumnForString("CLASS_ICON", VO.getCLASS_ICON());//分类图标
                sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
                sqlHelper.setWhereForInt("CLASS_ID", " = ", VO.getCLASS_ID());
                sqlHelper.update(ResourceManager.getConnection(), "修改商品分类");
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
     * 查询所有分类
     */
    public List<ProductClassVO> queryProductClass(int serviceId) {
        List<ProductClassVO> productClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_PRODUCT_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", ProductClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//按排序值倒序查询
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            productClassVOs = getProductClassColl(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return productClassVOs;
    }

    /**
     * id查询分类
     */
    public ProductClassVO queryProductClassById(int serviceId, int productClassId) {
        List<ProductClassVO> productClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_PRODUCT_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("CLASS_ID", " = ", productClassId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", ProductClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//按排序值倒序查询
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            productClassVOs = getProductClassColl(sql);
            if (productClassVOs.size() > 0) {
                return productClassVOs.get(0);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询该父类下所有子分类
     */
    public List<ProductClassVO> queryChlidProductClass(int serviceId, int ParentClassId) {
        List<ProductClassVO> productClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_PRODUCT_CLASS");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("PARENT_CLASS_ID", " = ", ParentClassId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", ProductClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//按排序值倒序
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            productClassVOs = getProductClassColl(sql);
            if (productClassVOs.size() > 0) {
                return productClassVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 根据SQL获取商品分类集合
     *
     * @param sql
     */
    public List<ProductClassVO> getProductClassColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductClassVO VO = new ProductClassVO();
                VO.setCLASS_ID(rs.getInt("CLASS_ID"));
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setCLASS_ORDER_NUM(rs.getInt("CLASS_ORDER_NUM"));//排序值
                VO.setPARENT_CLASS_ID(rs.getInt("PARENT_CLASS_ID"));//父分类ID
                VO.setIS_DEL(rs.getInt("IS_DEL"));//删除状态
                VO.setCLASS_NAME(rs.getString("CLASS_NAME"));//分类名称
                VO.setCLASS_PATH(rs.getString("CLASS_PATH"));//分类路径
                VO.setCLASS_ICON(rs.getString("CLASS_ICON"));//分类图标
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
