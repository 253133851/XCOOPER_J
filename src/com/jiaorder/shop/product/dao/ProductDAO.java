package com.jiaorder.shop.product.dao;

import com.jiaorder.shop.product.vo.ProductVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.logger.Logger;
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
 * 商品Dao
 *
 * Created by hh on 2016.2.25.
 */
public class ProductDAO {

  /**
   * 新增商品
   */
  public void addProduct(ProductVO VO) throws DataAccessException {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT");
      sqlHelper.setInsertForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setInsertForInt("CLASS_ID", VO.getCLASS_ID());
      sqlHelper.setInsertForInt("BRAND_ID", VO.getBRAND_ID());
      sqlHelper.setInsertForInt("IS_SALE", VO.getIS_SALE());
      sqlHelper.setInsertForInt("ORDER_NUM", VO.getORDER_NUM());
      sqlHelper.setInsertForInt("SALES_VOLUME", VO.getSALES_VOLUME());
      sqlHelper.setInsertForInt("UNIT_ID", VO.getUNIT_ID());
      sqlHelper.setInsertForInt("IS_DEL", VO.getIS_DEL());
      sqlHelper.setInsertForString("PRD_SPU", VO.getPRD_SPU());
      sqlHelper.setInsertForString("PRD_NAME", VO.getPRD_NAME());
      sqlHelper.setInsertForString("PRD_OTHER_NAME", VO.getPRD_OTHER_NAME());
      sqlHelper.setInsertForString("TAG1", VO.getTAG1());
      sqlHelper.setInsertForString("TAG2", VO.getTAG2());
      sqlHelper.setInsertForString("TAG3", VO.getTAG3());
      sqlHelper.setInsertForString("TAG4", VO.getTAG4());
      sqlHelper.setInsertForString("TAG5", VO.getTAG5());
      sqlHelper.setInsertForString("IMG", VO.getIMG());
      sqlHelper.setInsertForString("KEYWORD", VO.getKEYWORD());
      sqlHelper.setInsertForString("SALES_TITLE", VO.getSALES_TITLE());
      sqlHelper.setInsertForString("AD_IMG1", VO.getAD_IMG1());
      sqlHelper.setInsertForString("AD_IMG2", VO.getAD_IMG2());
      sqlHelper.setInsertForString("AD_IMG3", VO.getAD_IMG3());
      sqlHelper.setInsertForString("AD_IMG4", VO.getAD_IMG4());
      sqlHelper.setInsertForString("AD_IMG5", VO.getAD_IMG5());
      sqlHelper.setInsertForString("AD_IMG6", VO.getAD_IMG6());
      sqlHelper.setInsertForString("AD_IMG7", VO.getAD_IMG7());
      sqlHelper.setInsertForString("AD_IMG8", VO.getAD_IMG8());
      sqlHelper.setInsertForString("AD_IMG9", VO.getAD_IMG9());
      sqlHelper.setInsertForString("AD_IMG10", VO.getAD_IMG10());
      sqlHelper.setInsertForString("FILE_PATH1", VO.getFILE_PATH1());
      sqlHelper.setInsertForString("FILE_PATH2", VO.getFILE_PATH2());
      sqlHelper.setInsertForString("FILE_PATH3", VO.getFILE_PATH3());
      sqlHelper.setInsertForDatetime("ADD_DATETIME",
          DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.setInsertForDatetime("UPDATE_DATETIME",
          DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.setInsertForDatetime("DEL_DATETIME",
          DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.setInsertForInt("MAX_PRICE", VO.getMAX_PRICE());
      sqlHelper.setInsertForInt("MIN_PRICE", VO.getMIN_PRICE());
      sqlHelper.setInsertForInt("IS_SKU", VO.getIS_SKU());
    try {
      sqlHelper.insert(ResourceManager.getConnection(), "新增商品");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 删除商品
   *
   * @param ids id之间用逗号分隔开
   */
  public void delProduct(String ids, int serviceId) {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = ResourceManager.getConnection();
      statement = connection.createStatement();
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setOrGroupForInt("PRD_ID", "=", ids, true);
      sqlHelper.delete(connection, "删除商品");
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
   * 更新商品
   */
  public void updateProduct(int serviceId, ProductVO VO) throws DataAccessException {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT");
      sqlHelper.setColumnForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setColumnForInt("CLASS_ID", VO.getCLASS_ID());
      sqlHelper.setColumnForInt("BRAND_ID", VO.getBRAND_ID());
      sqlHelper.setColumnForInt("IS_SALE", VO.getIS_SALE());
      sqlHelper.setColumnForInt("ORDER_NUM", VO.getORDER_NUM());
      sqlHelper.setColumnForInt("SALES_VOLUME", VO.getSALES_VOLUME());
      sqlHelper.setColumnForInt("UNIT_ID", VO.getUNIT_ID());
      sqlHelper.setColumnForInt("IS_DEL", VO.getIS_DEL());
      sqlHelper.setColumnForString("PRD_SPU", VO.getPRD_SPU());
      sqlHelper.setColumnForString("PRD_NAME", VO.getPRD_NAME());
      sqlHelper.setColumnForString("PRD_OTHER_NAME", VO.getPRD_OTHER_NAME());
      sqlHelper.setColumnForString("TAG1", VO.getTAG1());
      sqlHelper.setColumnForString("TAG2", VO.getTAG2());
      sqlHelper.setColumnForString("TAG3", VO.getTAG3());
      sqlHelper.setColumnForString("TAG4", VO.getTAG4());
      sqlHelper.setColumnForString("TAG5", VO.getTAG5());
      sqlHelper.setColumnForString("IMG", VO.getIMG());
      sqlHelper.setColumnForString("KEYWORD", VO.getKEYWORD());
      sqlHelper.setColumnForString("SALES_TITLE", VO.getSALES_TITLE());
      sqlHelper.setColumnForString("AD_IMG1", VO.getAD_IMG1());
      sqlHelper.setColumnForString("AD_IMG2", VO.getAD_IMG2());
      sqlHelper.setColumnForString("AD_IMG3", VO.getAD_IMG3());
      sqlHelper.setColumnForString("AD_IMG4", VO.getAD_IMG4());
      sqlHelper.setColumnForString("AD_IMG5", VO.getAD_IMG5());
      sqlHelper.setColumnForString("AD_IMG6", VO.getAD_IMG6());
      sqlHelper.setColumnForString("AD_IMG7", VO.getAD_IMG7());
      sqlHelper.setColumnForString("AD_IMG8", VO.getAD_IMG8());
      sqlHelper.setColumnForString("AD_IMG9", VO.getAD_IMG9());
      sqlHelper.setColumnForString("AD_IMG10", VO.getAD_IMG10());
      sqlHelper.setColumnForString("FILE_PATH1", VO.getFILE_PATH1());
      sqlHelper.setColumnForString("FILE_PATH2", VO.getFILE_PATH2());
      sqlHelper.setColumnForString("FILE_PATH3", VO.getFILE_PATH3());
      sqlHelper.setColumnForDatetime("ADD_DATETIME",
          DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);
      sqlHelper.setColumnForDatetime("UPDATE_DATETIME",
          DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);
      sqlHelper.setColumnForDatetime("DEL_DATETIME",
          DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);

      sqlHelper.setColumnForInt("MAX_PRICE", VO.getMAX_PRICE());
      sqlHelper.setColumnForInt("MIN_PRICE", VO.getMIN_PRICE());
      sqlHelper.setColumnForInt("IS_SKU", VO.getIS_SKU());

      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setWhereForInt("PRD_ID", " = ", VO.getPRD_ID());
      sqlHelper.update(ResourceManager.getConnection(), "修改商品品牌");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 用id查询商品
   */
  public ProductVO queryProductById(int productId, int serviceId) {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setSelectColumn("*");
      sqlHelper.setTable("SHOP_PRODUCT");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setWhereForInt("PRD_ID", " = ", productId);
      String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
      List<ProductVO> list = getProductColl(sql);
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
   * 查询所有商品
   */
  public List<ProductVO> queryProduct(int serviceId) {
    List<ProductVO> productVOs = new ArrayList<>();
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setSelectColumn("*");
      sqlHelper.setTable("SHOP_PRODUCT");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);

      String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
      productVOs = getProductColl(sql);
      if (productVOs.size() > 0) {
        return productVOs;
      }
      return null;
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据SQL获取商品集合
   */
  public List<ProductVO> getProductColl(String sql) {
    List resultList = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      conn = ResourceManager.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        ProductVO VO = new ProductVO();
        VO.setAD_IMG1(rs.getString("AD_IMG1"));
        VO.setAD_IMG2(rs.getString("AD_IMG2"));
        VO.setAD_IMG3(rs.getString("AD_IMG3"));
        VO.setAD_IMG4(rs.getString("AD_IMG4"));
        VO.setAD_IMG5(rs.getString("AD_IMG5"));
        VO.setAD_IMG6(rs.getString("AD_IMG6"));
        VO.setAD_IMG7(rs.getString("AD_IMG7"));
        VO.setAD_IMG8(rs.getString("AD_IMG8"));
        VO.setAD_IMG9(rs.getString("AD_IMG9"));
        VO.setAD_IMG10(rs.getString("AD_IMG10"));
        VO.setBRAND_ID(rs.getInt("BRAND_ID"));
        VO.setCLASS_ID(rs.getInt("CLASS_ID"));
        VO.setFILE_PATH1(rs.getString("FILE_PATH1"));
        VO.setFILE_PATH2(rs.getString("FILE_PATH2"));
        VO.setFILE_PATH3(rs.getString("FILE_PATH3"));
        VO.setIMG(rs.getString("IMG"));
        VO.setIS_SALE(rs.getInt("IS_SALE"));
        VO.setKEYWORD(rs.getString("KEYWORD"));
        VO.setORDER_NUM(rs.getInt("ORDER_NUM"));
        VO.setPRD_ID(rs.getInt("PRD_ID"));
        VO.setPRD_NAME(rs.getString("PRD_NAME"));
        VO.setPRD_OTHER_NAME(rs.getString("PRD_OTHER_NAME"));
        VO.setPRD_SPU(rs.getString("PRD_SPU"));
        VO.setSALES_TITLE(rs.getString("SALES_TITLE"));
        VO.setSALES_VOLUME(rs.getInt("SALES_VOLUME"));
        VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
        VO.setTAG1(rs.getString("TAG1"));
        VO.setTAG2(rs.getString("TAG2"));
        VO.setTAG3(rs.getString("TAG3"));
        VO.setTAG4(rs.getString("TAG4"));
        VO.setTAG5(rs.getString("TAG5"));
        VO.setUNIT_ID(rs.getInt("UNIT_ID"));
        VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));
        VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME"));
        VO.setDEL_DATETIME(rs.getTimestamp("DEL_DATETIME"));
        VO.setMAX_PRICE(rs.getInt("MAX_PRICE"));
        VO.setMIN_PRICE(rs.getInt("MIN_PRICE"));
        VO.setIS_SKU(rs.getInt("IS_SKU"));
        VO.setIS_DEL(rs.getInt("IS_DEL"));

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


  public int getUnitUsedCount(int serviceId, int unitId) {
    int count = -1;

    try {

      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT");

      sh.setSelectColumn("count(*) COUNT");

      sh.setWhereForInt("service_id", "=", serviceId);
      sh.setWhereForInt("unit_id", "=", unitId);

      count = sh.selectAndGetIntValue(ResourceManager.getConnection(), "COUNT", "getUnitUsedCount");

    } catch (DataAccessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return count;
  }

  public void multiChangeSaleStateByIds(int serviceId, String prdIds, int saleSate) throws DataAccessException {
    try {

      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT");

      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setOrGroupForInt("PRD_ID", "=", prdIds, true);

      sh.setColumnForInt("IS_SALE", saleSate);

      String sql = sh.getSQL(sh.getUpdateSQL());

      Logger.d(sql);

      sh.update(ResourceManager.getConnection(), "multiChangeSaleStateByIds");

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public void multiDelProductByIds(int serviceId, String ids) throws DataAccessException {
    try {

      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT");

      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setOrGroupForInt("PRD_ID", "=", ids, true);
      sh.setColumnForInt("IS_DEL", 1);
      sh.setColumnForDatetime("DEL_DATETIME", DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",true);
      String sql = sh.getSQL(sh.getUpdateSQL());
      Logger.d(sql);
      sh.update(ResourceManager.getConnection(), "multiDelProductByIds");

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }

  }



  public ProductVO getProductVOById(int serviceId, int prdId) throws DataAccessException {
    SqlHelper sh = new SqlHelper();

    sh.setTable("SHOP_PRODUCT");

    sh.setSelectColumn("SHOP_PRODUCT.*");

    sh.setWhereForInt("SERVICE_ID","=", serviceId);
    sh.setWhereForInt("PRD_ID","=", prdId);

    List<ProductVO> list = getProductColl(sh.getSQL(sh.getSelectSQL()));

    if (!list.isEmpty()) {
      return list.get(0);
    } else {
      return null;
    }

  }
}
