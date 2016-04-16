package com.jiaorder.shop.productintro.busi;

import com.jiaorder.shop.productintro.dao.ProductIntroDAO;
import com.jiaorder.shop.productintro.vo.ProductIntroVO;
import com.pabula.fw.exception.DataAccessException;

/**
 * Created by sunsai on 2016/3/19 - 12:41.
 */
public class ProductIntroBean {

  private ProductIntroDAO dao = null;

  private ProductIntroBean() {
    dao = new ProductIntroDAO();
  }

  private static ProductIntroBean bean = null;

  public static ProductIntroBean newInstance() {
    if (null == bean) {
      synchronized (ProductIntroBean.class) {
        if (null == bean) {
          bean = new ProductIntroBean();
        }
      }
    }

    return bean;
  }

  public void addProductIntro(ProductIntroVO vo) throws DataAccessException{
     dao.addProductIntro(vo);
  }

  public ProductIntroVO getProductIntroByProductId(int serviceId, int prdId) throws DataAccessException {
    return dao.getProductIntroByProductId(serviceId, prdId);
  }

  public void updateProductIntro(int serviceId, ProductIntroVO vo) {
    dao.updateProductIntro(serviceId, vo);
  }

}
