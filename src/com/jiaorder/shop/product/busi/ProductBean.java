package com.jiaorder.shop.product.busi;

import com.jiaorder.shop.product.dao.ProductDAO;
import com.jiaorder.shop.product.vo.ProductJson;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.shop.unit.busi.UnitBean;
import com.jiaorder.shop.unit.vo.UnitVO;
import com.pabula.common.logger.Logger;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunsai on 2016/3/7.
 */
public class ProductBean {

  public static final int SALE_STATE_ALL = 0;//上下架全部状态
  public static final int SALE_STATE_YES = 1;// 上架
  public static final int SALE_STATE_NO = -1;// 下架
  public static final int TAG_ALL = 0;
  public static final int NOT_DELETE = -1;
  public static final int HAS_DELETED = 1;

  private ProductDAO dao;

  private ProductBean() {
    dao = new ProductDAO();
  }

  private static ProductBean bean = null;

  public static ProductBean newInstance() {
    if (null == bean) {
      synchronized (ProductBean.class) {
        if (null == bean) {
          bean = new ProductBean();
        }
      }
    }
    return bean;
  }

  /**
   * 添加商品
   * @param productVO
   * @return
   */
  public void addProduct(ProductVO productVO) throws DataAccessException{
    if (productVO.getPRD_ID() < 1) {
      productVO.setPRD_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT"));
    }

    dao.addProduct(productVO);
  }

  /**
   * 根据sql语句 ， h获取ProductJson列表
   * @param serviceId
   * @param sql
   * @return
   */
  public List<ProductJson> getProductJsonCollBySql(int serviceId, String sql) {

    //去数据库中查询product列表
    List<ProductVO> productVOList = dao.getProductColl(sql);

    List<ProductJson> productJsonList = new ArrayList<>();

    //根据列表获得prdIds 的字符串形式
    String prdIds = getPrdIdsByList(productVOList);

    //根据prdIds 查找skuList
    List<ProductSkuVO> productSkuVOList = ProductSkuBean.newInstance().getSkuCollByProductIDS(serviceId, prdIds);

    //转换成map， 建是prdId， 值是该prdId下面的sku列表
    Map<Integer, List<ProductSkuVO>> map =  ProductSkuBean.newInstance().prdSkuListToMapList(productSkuVOList);

    /**
     * 把product 和sku组合成一个对象 productJson
     */
    for(ProductVO product : productVOList) {
      List<ProductSkuVO> skuList = map.get(product.getPRD_ID());
      if (null != skuList) {
        productJsonList.addAll(getProductJsonBySpuAndSkus(product, skuList));
      }
    }

    //这里是把productJson组合上单位名称
    //只所以没有放到上面的循环中吗， 是因为想把这种不重要的属性，不一点每个请求都需要所有的，
    //所以做成可插拔式的
    List<UnitVO> unitList = UnitBean.newInstance().getUnitColl(serviceId);
    setProductJsonUnitName(productJsonList, unitList);

    return productJsonList;
  }




  /** 查询一个单位被引用了几次， 用于删除单位是检查
   *
   * @param serviceId serviceId
   * @param unitId 将要删除的单位ID
   * @return -1 为执行失败， 0 - N 表示引用的次数
   */
  public int getUnitUsedCount(int serviceId, int unitId) {
    return dao.getUnitUsedCount(serviceId, unitId);
  }


  /**
   * 根据列表获取使用逗号拼接的 id字符串，
   * @param productVOList
   * @return
   */
  private String getPrdIdsByList(List<ProductVO> productVOList) {
    StringBuilder sb = null;
    for (ProductVO vo : productVOList) {
      if (null == sb) {
        sb = new StringBuilder();
      } else {
        sb.append(",");
      }
      sb.append(vo.getPRD_ID());
    }
    return null == sb ? "" : sb.toString();
  }

  /**
   *
   * @param serviceId
   * @param prdIds
   * @param saleState
   * @return
   */
  public void multiChangeSaleStateByIds(int serviceId, String prdIds, int saleState) throws DataAccessException{
     dao.multiChangeSaleStateByIds(serviceId, prdIds, saleState);
  }

  /**
   * 根据prdid 批量删除product
   * 多个id用逗号隔开
   * @param serviceId
   * @param ids
   * @return
   */
  public void multiDelProductByIds(int serviceId, String ids) throws DataAccessException{
        //TODO  需要先检查有无未处理完的订单 ， 暂时做不了
      //先删除product
      dao.multiDelProductByIds(serviceId, ids);
      //再删除sku
      ProductSkuBean.newInstance().multiDelSkuByPrdIds(serviceId, ids);
  }

  //生成一个先的spu编码
  public String createProductSpuCode(int serviceId) {
    try {
      int code = SeqNumHelper.getNewSeqNum(serviceId, "PRODUCT_SPU_CODE");
      String spuCode = "p" + code;
      return spuCode;
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * get by id
   * @param serviceId
   * @param prdId
   * @return
   * @throws DataAccessException
   */
  public ProductVO getProductVOById(int serviceId, int prdId) throws DataAccessException{
   return dao.getProductVOById(serviceId, prdId);
  }

  /**
   * 把一个商品的sku都组装起来
   * @param productVO
   * @param skuVOList
   * @return
   */
  public List<ProductJson> getProductJsonBySpuAndSkus(ProductVO productVO, List<ProductSkuVO> skuVOList) {
    List<ProductJson> resultList = new ArrayList<>();
    for (ProductSkuVO skuVo : skuVOList) {
      ProductJson productJson = new ProductJson();

      productJson.setProduct(productVO);
      productJson.setSku(skuVo);

      resultList.add(productJson);
    }
    return resultList;
  }

  /**
   * 为productJson列表设置单位名称
   * @param productJsonList
   * @param unitVOList
   * @return
   */
  public void setProductJsonUnitName(List<ProductJson> productJsonList, List<UnitVO> unitVOList) {

    //把单位转换成以unitid为key，unitVO为value 的map
    Map<Integer, UnitVO> unitVOMap = UnitBean.newInstance().convertListToMap(unitVOList);

    for(ProductJson productJson : productJsonList) {
      if (unitVOMap.get(productJson.getUnitId()) != null) {
        productJson.setUnitName(unitVOMap.get(productJson.getUnitId()).getUNIT());
      }
    }
  }


  //更新product
  public void updateProduct(int serviceId, ProductVO product) throws DataAccessException{
     dao.updateProduct(serviceId, product);
  }




  public static void main(String[] args) {
    ProductBean bean = newInstance();
    Logger.d(bean.createProductSpuCode(1));
  }


}
