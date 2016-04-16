package com.jiaorder.shop.sku.dao;

import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
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
 * Created by hh on 2016.2.25.
 */
public class ProductSkuDAO {

  private static List<ProductSkuVO> EMPTY_SKU_LIST = new ArrayList<>();

  /**
   * 新增
   */
  public void addProductSku(ProductSkuVO VO) throws DataAccessException {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_SKU");
      sqlHelper.setInsertForInt("SKU_ID", VO.getSKU_ID());
      sqlHelper.setInsertForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setInsertForInt("SALES_VOLUME", VO.getSALES_VOLUME());
      sqlHelper.setInsertForInt("STOCK", VO.getSTOCK());
      sqlHelper.setInsertForInt("IS_SALE", VO.getIS_SALE());
      sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setInsertForInt("IS_DEL", ProductSkuBean.NOT_DELETE);
      sqlHelper.setInsertForString("PRD_SKU", VO.getPRD_SKU());
      sqlHelper.setInsertForString("SKU_NAME1", VO.getSKU_NAME1());
      sqlHelper.setInsertForString("SKU_NAME2", VO.getSKU_NAME2());
      sqlHelper.setInsertForString("SKU_NAME3", VO.getSKU_NAME3());
      sqlHelper.setInsertForString("SKU_CONTENT1", VO.getSKU_CONTENT1());
      sqlHelper.setInsertForString("SKU_CONTENT2", VO.getSKU_CONTENT2());
      sqlHelper.setInsertForString("SKU_CONTENT3", VO.getSKU_CONTENT3());
      sqlHelper.setInsertForInt("OLD_PRICE", VO.getOLD_PRICE());
      sqlHelper.setInsertForInt("PRICE", VO.getPRICE());
      sqlHelper.setInsertForInt("COST", VO.getCOST());
      sqlHelper.setInsertForString("IMG", VO.getIMG());
      sqlHelper.setInsertForString("BAR_CODE", VO.getBAR_CODE());
      sqlHelper.setInsertForDatetime("ADD_DATETIME",
          DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.setInsertForDatetime("UPDATE_DATETIME",
          DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.setInsertForDatetime("DEL_DATETIME",
          DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);
      sqlHelper.insert(ResourceManager.getConnection(), "新增");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 删除
   *
   * @param ids id之间用逗号分隔开
   */
  public void delProductSku(String ids, int serviceId) {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = ResourceManager.getConnection();
      statement = connection.createStatement();
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_SKU");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setOrGroupForInt("SKU_ID", "=", ids, true);
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
   * 更新sku
   */
  public void updateProductSku(int serviceId, ProductSkuVO VO) throws DataAccessException {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setTable("SHOP_PRODUCT_SKU");
      sqlHelper.setColumnForInt("SKU_ID", VO.getSKU_ID());
      sqlHelper.setColumnForInt("PRD_ID", VO.getPRD_ID());
      sqlHelper.setColumnForInt("SALES_VOLUME", VO.getSALES_VOLUME());
      sqlHelper.setColumnForInt("STOCK", VO.getSTOCK());
      sqlHelper.setColumnForInt("IS_SALE", VO.getIS_SALE());
      sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
      sqlHelper.setColumnForInt("IS_DEL", VO.getIS_DEL());
      sqlHelper.setColumnForString("PRD_SKU", VO.getPRD_SKU());
      sqlHelper.setColumnForString("SKU_NAME1", VO.getSKU_NAME1());
      sqlHelper.setColumnForString("SKU_NAME2", VO.getSKU_NAME2());
      sqlHelper.setColumnForString("SKU_NAME3", VO.getSKU_NAME3());
      sqlHelper.setColumnForString("SKU_CONTENT1", VO.getSKU_CONTENT1());
      sqlHelper.setColumnForString("SKU_CONTENT2", VO.getSKU_CONTENT2());
      sqlHelper.setColumnForString("SKU_CONTENT3", VO.getSKU_CONTENT3());
      sqlHelper.setColumnForInt("OLD_PRICE", VO.getOLD_PRICE());
      sqlHelper.setColumnForInt("PRICE", VO.getPRICE());
      sqlHelper.setColumnForInt("COST", VO.getCOST());
      sqlHelper.setColumnForString("IMG", VO.getIMG());
      sqlHelper.setColumnForString("BAR_CODE", VO.getBAR_CODE());
      sqlHelper.setColumnForDatetime("ADD_DATETIME",
          DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);
      sqlHelper.setColumnForDatetime("UPDATE_DATETIME",
          DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);
      sqlHelper.setColumnForDatetime("DEL_DATETIME",
          DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setWhereForInt("SKU_ID", " = ", VO.getSKU_ID());
      sqlHelper.update(ResourceManager.getConnection(), "修改商品sku");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 用id查询
   */
  public ProductSkuVO queryProductSkuById(int productSkuId, int serviceId) {
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setSelectColumn("*");
      sqlHelper.setTable("SHOP_PRODUCT_SKU");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      sqlHelper.setWhereForInt("SKU_ID", " = ", productSkuId);
      String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
      List<ProductSkuVO> list = getProductSkuColl(sql);
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
  public List<ProductSkuVO> queryProductSku(int serviceId) {
    List<ProductSkuVO> productSkuVOs = new ArrayList<>();
    try {
      SqlHelper sqlHelper = new SqlHelper();
      sqlHelper.setSelectColumn("*");
      sqlHelper.setTable("SHOP_PRODUCT_SKU");
      sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
      String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
      productSkuVOs = getProductSkuColl(sql);
      if (productSkuVOs.size() > 0) {
        return productSkuVOs;
      }
      return null;
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据SQL获取集合
   */
  public List<ProductSkuVO> getProductSkuColl(String sql) {
    List resultList = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      conn = ResourceManager.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        ProductSkuVO VO = new ProductSkuVO();
        VO.setBAR_CODE(rs.getString("BAR_CODE"));
        VO.setCOST(rs.getInt("COST"));
        VO.setIMG(rs.getString("IMG"));
        VO.setIS_SALE(rs.getInt("IS_SALE"));
        VO.setOLD_PRICE(rs.getInt("OLD_PRICE"));
        VO.setPRD_ID(rs.getInt("PRD_ID"));
        VO.setPRD_SKU(rs.getString("PRD_SKU"));
        VO.setPRICE(rs.getInt("PRICE"));
        VO.setSALES_VOLUME(rs.getInt("SALES_VOLUME"));
        VO.setSKU_ID(rs.getInt("SKU_ID"));
        VO.setSTOCK(rs.getInt("STOCK"));
        VO.setIS_DEL(rs.getInt("IS_DEL"));
        VO.setSKU_CONTENT1(rs.getString("SKU_CONTENT1"));
        VO.setSKU_CONTENT2(rs.getString("SKU_CONTENT2"));
        VO.setSKU_CONTENT3(rs.getString("SKU_CONTENT3"));
        VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
        VO.setSKU_NAME1(rs.getString("SKU_NAME1"));
        VO.setSKU_NAME2(rs.getString("SKU_NAME2"));
        VO.setSKU_NAME3(rs.getString("SKU_NAME3"));
        VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME"));
        VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME"));
        VO.setDEL_DATETIME(rs.getTimestamp("DEL_DATETIME"));
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

  public List<ProductSkuVO> getSkuCollByProductIDS(int serviceId, String prdIds) {

    try {

      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT_SKU");

      sh.setSelectColumn("*");

      sh.setWhereForInt("SERVICE_ID", "=", serviceId);

      sh.setOrGroupForInt("PRD_ID", "=", prdIds, true);

      sh.setWhereForInt("IS_DEL", "=", ProductSkuBean.NOT_DELETE);

      String sql = sh.getSQL(sh.getSelectSQL());

      Logger.d(sql);
      return getProductSkuColl(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EMPTY_SKU_LIST;
  }

  /**
   * 根据skuIds，批量改变sku上下架状态
   * @param serviceId
   * @param skuIds
   * @param saleState
   * @return
   */
  public void multiChangeSaleStateBySkuIds(int serviceId, String skuIds, int saleState) throws DataAccessException {
    try {
      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT_SKU");

      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setOrGroupForInt("SKU_ID", "=", skuIds, true);
      sh.setColumnForInt("IS_SALE", saleState);

      String sql = sh.getSQL(sh.getUpdateSQL());

      Logger.d(sql);

      sh.update(ResourceManager.getConnection(), "multiChangeSaleStateBySkuIds");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  public void multiDelSkuByPrdIds(int serviceId, String prdIds) throws DataAccessException {
    multiDelSkuByIds(serviceId, "PRD_ID", prdIds);
  }

  public void multiDelSkuBySkuIds(int serviceId, String skuIds) throws DataAccessException {
    multiDelSkuByIds(serviceId, "SKU_ID", skuIds);
  }

  private void multiDelSkuByIds(int serviceId, String delBy, String ids) throws DataAccessException {
    try {
      SqlHelper sh = new SqlHelper();

      sh.setTable("SHOP_PRODUCT_SKU");
      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setOrGroupForInt(delBy, "=", ids, true);

      sh.setColumnForInt("IS_DEL", 1);
      sh.setColumnForDatetime("DEL_DATETIME",
          DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss",
          true);

      String sql = sh.getSQL(sh.getUpdateSQL());
      Logger.d(sql);

      sh.update(ResourceManager.getConnection(), "multiDelSkuByPrdIds");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }

  /**
   * 根据skuid 删除product
   * @param serviceId
   * @param skuIds
   * @throws DataAccessException
   */
  public void delProductByDelAllSku(int serviceId, String skuIds) throws DataAccessException {
    Statement stmt = null;
    Connection conn = null;
    try {
      String sql = "update shop_product sp inner join shop_product_sku spk on spk.prd_id = sp.prd_id set sp.is_del = "
              + ProductBean.HAS_DELETED
              + " , sp.DEL_DATETIME = '"
              + DateUtil.format(DateUtil.getCurrTime(), "yyyy-MM-dd HH:mm:ss")
              + "' where sp.service_id = "
              + serviceId
              + " and sp.prd_id > 0 and spk.sku_id in ("
              + skuIds
              + ");";

      Logger.d(sql);

      conn = ResourceManager.getConnection();
      stmt = conn.createStatement();
      stmt.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    } finally {
      if (null != stmt) {
        ResourceManager.close(stmt);
      }
      if (null != conn) {
        ResourceManager.close(conn);
      }
    }
  }

  /**
   * 检查一个sku的兄弟中是否有没有删除的
   * @param serviceId
   * @param skuId
   * @return
   * @throws DataAccessException
   */
  public int countSkuIsLastOneAfterDel(int serviceId, int skuId) throws DataAccessException {
    int count = -1;
    Statement stmt = null;
    Connection conn = null;
    try {

      String sql = "SELECT  count(a.SKU_ID) COUNT FROM SHOP_PRODUCT_SKU a "
          + "where a.service_id = " + serviceId
          + " and a.is_del = -1 and a.prd_id = "
          + "(select b.prd_id from SHOP_PRODUCT_SKU b  "
          + "where b.service_id = " + serviceId + " and b.sku_id = " + skuId + " ) ;";
      Logger.d(sql);
      conn = ResourceManager.getConnection();
      stmt = conn.createStatement();

      ResultSet resultSet = stmt.executeQuery(sql);
      resultSet.next();
      count = resultSet.getInt("COUNT");

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
    return count;
  }

  public static void main(String[] args) {
    ProductSkuDAO dao = new ProductSkuDAO();
    //dao.delProductByDelAllSku(1, "8");
  }
}
