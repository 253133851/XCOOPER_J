package com.jiaorder.shop.sku.busi;

import com.jiaorder.shop.sku.dao.ProductSkuDAO;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.logger.Logger;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunsai on 2016/3/7.
 */
public class ProductSkuBean {

  public static final int NOT_DELETE = -1;
  public static final int HAS_DELETED = 1;

  private ProductSkuDAO dao;

  private static List<ProductSkuVO> EMPTY_SKU_LIST = new ArrayList<>();

  private ProductSkuBean() {
    dao = new ProductSkuDAO();
  }

  private static ProductSkuBean bean = null;

  public static ProductSkuBean newInstance() {
    if (null == bean) {
      synchronized (ProductSkuBean.class) {
        if (null == bean) {
          bean = new ProductSkuBean();
        }
      }
    }
    return bean;
  }

  public void addProductSku(ProductSkuVO skuVo) throws DataAccessException {
    if (skuVo.getSKU_ID() < 1) {
      skuVo.setSKU_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT_SKU"));
    }
    dao.addProductSku(skuVo);
  }

  public ProductSkuVO getSkuBySkuId(int serviceId, int skuId) throws DataAccessException {
    List<ProductSkuVO> result = getSkuCollBySkuIds(serviceId, String.valueOf(skuId));
    if (!result.isEmpty()) {
      return result.get(0);
    } else {
      return null;
    }
  }

  public List<ProductSkuVO> getSkuCollBySkuIds(int serviceId, String skuIds)
      throws DataAccessException {
    SqlHelper sh = new SqlHelper();
    sh.setTable("SHOP_PRODUCT_SKU");
    sh.setSelectColumn("*");
    sh.setWhereForInt("SERVICE_ID", "=", serviceId);

    sh.setOrGroupForInt("SKU_ID", "=", skuIds, true);

    String sql = sh.getSQL(sh.getSelectSQL());
    return dao.getProductSkuColl(sql);
  }

  public List<ProductSkuVO> getSkuCollByProductIDS(int serviceId, String prdIds) {
    if (StrUtil.isNull(prdIds)) {
      return EMPTY_SKU_LIST;
    }

    return dao.getSkuCollByProductIDS(serviceId, prdIds);
  }

  public void multiChangeSaleStateBySkuIds(int serviceId, String skuIds, int saleState)
      throws DataAccessException {
    dao.multiChangeSaleStateBySkuIds(serviceId, skuIds, saleState);
  }

  /**
   *
   * @param serviceId
   * @param skuIds
   * @return
   */
  private void delProductByDelAllSku(int serviceId, String skuIds) throws DataAccessException {
    if (StrUtil.isNotNull(skuIds)) {
      dao.delProductByDelAllSku(serviceId, skuIds);
    }
  }

  /**
   *
   * @param serviceId
   * @param prdIds
   * @return
   */
  public void multiDelSkuByPrdIds(int serviceId, String prdIds) throws DataAccessException {
    dao.multiDelSkuByPrdIds(serviceId, prdIds);
  }

  /**
   * 批量删除sku 如果删除成功之后会检测是否已经删除所有的sku 如果是的则删除所属的product
   */
  public void multiDelSkuBySkuIds(int serviceId, String skuIds) throws DataAccessException {

    //先根据skuIds删除 sku
    dao.multiDelSkuBySkuIds(serviceId, skuIds);

    //然后是检查该sku所属的prd是否已经删除所有的sku 了，

    String willDelPrdIds = getPrdIdsIfItsAllHasDel(serviceId, skuIds);

    //下面是删除这些product
    if (StrUtil.isNotNull(willDelPrdIds)) {
      //在sku自己这里处理怎么删除product，
      delProductByDelAllSku(serviceId, willDelPrdIds);
    }
  }

  /**
   * 根据skuIds检查哪些product已经删除了所有的sku，
   * 如果全部都删除了， 则把prdid包含再返回列表里
   * @param serviceId
   * @param skuIds
   * @return
   */
  private String getPrdIdsIfItsAllHasDel(int serviceId, String skuIds) throws DataAccessException {
    String[] skuIdArray = skuIds.split(",");
    StringBuilder result = null;
    final int hasDeleteAllSku = 0;
    for(String skuId : skuIdArray) {
      int count = countSkuIsLastOneAfterDel(serviceId, StrUtil.getNotNullIntValue(skuId));
      if (count == hasDeleteAllSku) {
        if (null == result) {
          result = new StringBuilder();
        } else {
          result.append(",");
        }
        result.append(skuId);
      }
    }

    return result == null ? "" : result.toString();
  }

  /**
   * 删除sku时，统计这个sku是否是该商品下面的最后一个， 如果是的则需要同步把那个product也置为删除 在删除之后执行， 如果返回的是0 则表明刚刚删除的是最后一个sku
   */
  public int countSkuIsLastOneAfterDel(int serviceId, int skuId) throws DataAccessException {
      return dao.countSkuIsLastOneAfterDel(serviceId, skuId);
  }

  /**
   * 检查一组prdSKu编码那个是否被使用过
   *
   * @return 返回第一个被使用过的编码，如果没有，则返回 null
   * @throws DataAccessException
   */
  public String checkHasUsedPrdSkuCode(int serviceId, String skuCodes)
      throws DataAccessException {

    String hasUserSkuCode = null;
    try {
      SqlHelper sh = new SqlHelper();
      sh.setTable("SHOP_PRODUCT_SKU");
      sh.setSelectColumn("PRD_SKU");
      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setOrGroupForString("PRD_SKU", "=", skuCodes, true);
      sh.setGROUP("PRD_SKU HAVING COUNT(PRD_SKU) > 0", true);
      String sql = sh.getSQL(sh.getSelectSQL());
      Logger.d(sql);

      hasUserSkuCode = sh.selectAndGetStringValue(ResourceManager.getConnection(), "PRD_SKU",
          "检查并获取已经被使用的prdsku编码");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
    return hasUserSkuCode;
  }

  public void updateProductSku(int serviceId, ProductSkuVO productSkuVo)
      throws DataAccessException {
    dao.updateProductSku(serviceId, productSkuVo);
  }

  public Map<Integer, ProductSkuVO> convertListToMap(List<ProductSkuVO> skuList) {
    Map<Integer, ProductSkuVO> result = new HashMap<>();
    if (null == skuList) {
      return result;
    }
    for (ProductSkuVO vo : skuList) {
      result.put(vo.getSKU_ID(), vo);
    }

    return result;
  }

  /**
   * 把skuid 拼成字符串，用逗号隔开
   */
  public String getSkuIdsByList(List<ProductSkuVO> productSkuVOList) {
    StringBuilder sb = null;
    for (ProductSkuVO vo : productSkuVOList) {

      if (null == sb) {
        sb = new StringBuilder();
      } else {
        sb.append(",");
      }
      sb.append(vo.getSKU_ID());
    }

    return null == sb ? null : sb.toString();
  }

  //把sku转换成以prdId 为key ，以prdid下所有的sku为list值
  public Map<Integer, List<ProductSkuVO>> prdSkuListToMapList(List<ProductSkuVO> productSkuVOList) {
    Map<Integer, List<ProductSkuVO>> map = new HashMap<>();
    List<ProductSkuVO> list = null;
    for (ProductSkuVO vo : productSkuVOList) {
      list = map.get(vo.getPRD_ID());
      if (null == list) {
        list = new ArrayList<>();
        map.put(vo.getPRD_ID(), list);
      }
      list.add(vo);
    }
    return map;
  }

  public static void main(String[] args) throws DataAccessException {
    //ProductSkuBean bean = newInstance();
    //
    //String result = bean.checkHasUsedPrdSkuCode(1, "121334");
    //
    //Logger.d(result);

    String a = "";

    String[] b = a.split(",");

    Logger.d("" + b.length);
  }
}
