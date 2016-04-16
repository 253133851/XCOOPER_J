package com.jiaorder.shop.product.web.command;

import com.jiaorder.shop.price.busi.ProductPriceMemberLevelBean;
import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.shop.product.web.util.LoadFormDataUtil;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunsai on 2016/3/22 - 17:35.
 */
public class CAjaxModifyProductPriceCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {


    int serviceId = UserHelper.getServiceID(request);
    String type = request.getParameter("modifyType");
    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"),0);
    int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuId"),0);
    int price = StrUtil.convertToIntMoney(request.getParameter("price"));
    int cost = StrUtil.convertToIntMoney(request.getParameter("cost"));

    ProductVO productVO = ProductBean.newInstance().getProductVOById(serviceId, prdId);

    List<ProductSkuVO> productSkuVOList = new ArrayList<>();

    if ("spu".equals(type)) {

      /**
       * 更新商品的最高价格和最低价格
       */
      productVO.setMIN_PRICE(price);
      productVO.setMAX_PRICE(price);

      productSkuVOList = ProductSkuBean.newInstance().getSkuCollByProductIDS(serviceId, String.valueOf(prdId));
    } else if ("sku".equals(type)) {

      /**
       * 更新商品的最高价格和最低价格
       */
      if (productVO.getMAX_PRICE() < price) {
        productVO.setMAX_PRICE(price);
      }
      if (productVO.getMIN_PRICE() > price) {
        productVO.setMIN_PRICE(price);
      }

      ProductSkuVO skuVO = ProductSkuBean.newInstance().getSkuBySkuId(serviceId, skuId);
      productSkuVOList.add(skuVO);
    }

    ProductBean.newInstance().updateProduct(serviceId, productVO);

    List<ProductPriceMemberLevelVO> productPriceMemberLevelVOList = LoadFormDataUtil.getProductPriceMemberLevelList(serviceId,request);

    /**
     * 更新所有的sku的价格
     */
    for (ProductSkuVO skuVO : productSkuVOList) {
      skuVO.setPRICE(price);
      skuVO.setCOST(cost);
      ProductSkuBean.newInstance().updateProductSku(serviceId, skuVO);
    }


    String skuIds = ProductSkuBean.newInstance().getSkuIdsByList(productSkuVOList);

    //下面这个操作，直接把之前该商品下面的所有sku对应的 根据用户级别进行特别定价的先全部删除，然后重新添加新的对应关系
    //应该写一个增量更新的算法
    ProductPriceMemberLevelBean.newInstance().delProductPricememberLevelBySkuIds(serviceId, skuIds);


    //把添加商品那边的也改成这样
    ProductPriceMemberLevelBean.newInstance().addProductPriceMemberLevelBySkuIds(skuIds, productPriceMemberLevelVOList);

    return JsonResultUtil.ok();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

    String type = request.getParameter("modifyType");

    if (!"sku".equals(type) && !"spu".equals(type)) {
      validate.addError("参数错误，请重试");
    }

    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"),0);
    if (0 == prdId) {
      validate.addError("参数错误请刷新重试！");
    }
  }
}
