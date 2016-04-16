package com.jiaorder.shop.sku.web.command;

import com.jiaorder.shop.price.busi.ProductPriceMemberLevelBean;
import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.SeqNumHelper;
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
 * Created by sunsai on 2016/3/21 - 16:04.
 */
public class CAjaxAddSkuCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {

    int serviceId = UserHelper.getServiceID(request);

    ProductSkuVO sku = new ProductSkuVO();
    sku.setSKU_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT_SKU"));
    sku.setPRD_ID(StrUtil.getNotNullIntValue(request.getParameter("prdId")));

    sku.setIS_SALE(1);
    sku.setSERVICE_ID(serviceId);
    sku.setIMG(request.getParameter("img"));
    sku.setPRD_SKU(request.getParameter("skuCode"));
    sku.setBAR_CODE(request.getParameter("barCode"));
    sku.setCOST(StrUtil.convertToIntMoney(request.getParameter("cost")));
    sku.setPRICE(StrUtil.convertToIntMoney(request.getParameter("price")));
    sku.setSKU_NAME1(StrUtil.getNotNullStringValue(request.getParameter("skuName1"),""));
    sku.setSKU_NAME2(StrUtil.getNotNullStringValue(request.getParameter("skuName2"),""));
    sku.setSKU_NAME3(StrUtil.getNotNullStringValue(request.getParameter("skuName3"),""));
    sku.setSKU_CONTENT1(StrUtil.getNotNullStringValue(request.getParameter("skuContent1"),""));
    sku.setSKU_CONTENT2(StrUtil.getNotNullStringValue(request.getParameter("skuContent2"),""));
    sku.setSKU_CONTENT3(StrUtil.getNotNullStringValue(request.getParameter("skuContent3"),""));

    ProductSkuBean.newInstance().addProductSku(sku);

    //加载会员级别对应的价格列表
    List<ProductPriceMemberLevelVO> productPriceMemberLevelVOList = loadPrdPriceMemLevelList(serviceId, sku.getSKU_ID(), request);

    //批量添加
    ProductPriceMemberLevelBean.newInstance().addProductPriceMemberLevel(productPriceMemberLevelVOList);


    return JsonResultUtil.instance().addData(sku.getSKU_ID()).json();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

    String skuCode = request.getParameter("skuCode");

    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"), 0);
    int serviceId = UserHelper.getServiceID(request);
    if (prdId == 0) {
      validate.addError("出错了， 请刷新重试");
    }


    /**
     * 检查sku编码是否有重复
     */
    try {
      if (StrUtil.isNotNull(skuCode)) {
        String hasUsedSkuCode =
            ProductSkuBean.newInstance().checkHasUsedPrdSkuCode(serviceId, skuCode);
        if (StrUtil.isNotNull(hasUsedSkuCode)) {
          validate.addError("商品规格编码：" + hasUsedSkuCode + " 已经被使用，请重新输入！");
        }
      }
    } catch (DataAccessException e) {
      e.printStackTrace();
    }




  }

  private List<ProductPriceMemberLevelVO> loadPrdPriceMemLevelList(int serviceId, int skuId, HttpServletRequest request) throws DataAccessException{

    int memberLevelCount = StrUtil.getNotNullIntValue(request.getParameter("memberLevelCount"), 0);

    List<ProductPriceMemberLevelVO> productPriceMemberList = new ArrayList<>();
    for (int i = 0; i < memberLevelCount; i++) {
      ProductPriceMemberLevelVO vo = new ProductPriceMemberLevelVO();


      //设置参数
      vo.setCAN_BUY("true".equals(request.getParameter("memberLevels[" + i + "][canBuy]")) ? 1 : -1);
      vo.setMIN_NUM(StrUtil.getNotNullIntValue(request.getParameter("memberLevels[" + i + "][minNum]"), 0));
      vo.setPRICE(StrUtil.convertToIntMoney(request.getParameter("memberLevels[" + i + "][price]")));
      vo.setMEMBER_LEVEL_ID(StrUtil.getNotNullIntValue(request.getParameter("memberLevels[" + i + "][member_LEVEL_ID]"), 0));


      vo.setSKU_ID(skuId);
      vo.setSERVICE_ID(serviceId);

      productPriceMemberList.add(vo);
    }

    return productPriceMemberList;
  }



}
