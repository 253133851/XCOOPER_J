package com.jiaorder.shop.productintro.dao;

import com.jiaorder.shop.productintro.vo.ProductIntroVO;
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
 * Created by hh on 2016.2.25.
 */
public class ProductIntroDAO {

  private int INTRO_ID;
  private int PRD_ID;
  private int SERVICE_ID;
  private String PRD_INTRO;

  /**
   * 新增产品描述
   */
  public void addProductIntro(ProductIntroVO VO) throws DataAccessException {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_INTRO");
      sqlHelper.setInsertForInt("INTRO_ID", VO.getINTRO_ID());
      sqlHelper.setInsertForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setInsertForString("PRD_INTRO", VO.getPRD_INTRO());
      sqlHelper.insert(ResourceManager.getConnection(), "新增产品描述");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 删除产品描述
   *
   * @param ids id之间用逗号分隔开
   */
  public void delProductIntro(String ids, int serviceId) {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = ResourceManager.getConnection();
      statement = connection.createStatement();
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_INTRO");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setOrGroupForInt("INTRO_ID", "=", ids, true);
      sqlHelper.delete(connection, "删除产品描述");
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
   * 更新产品描述
   */
  public void updateProductIntro(int serviceId, ProductIntroVO VO) {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_INTRO");
      sqlHelper.setColumnForInt("INTRO_ID", VO.getINTRO_ID());
      sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setColumnForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setColumnForString("PRD_INTRO", VO.getPRD_INTRO());
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setWhereForInt("INTRO_ID", " = ", VO.getINTRO_ID());
      sqlHelper.update(ResourceManager.getConnection(), "修改产品描述");
    } catch (DataAccessException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 用id查询产品描述
   */
  public ProductIntroVO getProductIntroByProductId(int serviceId, int prdId)
      throws DataAccessException {
    SqlHelper sqlHelper = new SqlHelper();
    sqlHelper.setSelectColumn("*");
    sqlHelper.setTable("SHOP_PRODUCT_INTRO");
    sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
    sqlHelper.setWhereForInt("PRD_ID", " = ", prdId);
    String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
    List<ProductIntroVO> list = getProductIntroColl(sql);
    if (list.size() > 0) {
      return list.get(0);
    }
    return null;
  }

  /**
   * 查询所有产品描述
   */
  public List<ProductIntroVO> queryProductIntro(int serviceId) {
    List<ProductIntroVO> unitVOs = new ArrayList<>();
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setSelectColumn("*");
      sqlHelper.setTable("SHOP_PRODUCT_INTRO");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
      unitVOs = getProductIntroColl(sql);
      if (unitVOs.size() > 0) {
        return unitVOs;
      }
      return null;
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据SQL获取商品描述集合
   */
  public List<ProductIntroVO> getProductIntroColl(String sql) {
    List resultList = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      conn = ResourceManager.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        ProductIntroVO VO = new ProductIntroVO();
        VO.setINTRO_ID(rs.getInt("INTRO_ID"));
        VO.setPRD_ID(rs.getInt("PRD_ID"));
        VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
        VO.setPRD_INTRO(rs.getString("PRD_INTRO"));
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
