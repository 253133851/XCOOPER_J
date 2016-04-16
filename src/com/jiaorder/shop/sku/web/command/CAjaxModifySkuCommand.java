package com.jiaorder.shop.sku.web.command;

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

/**
 * Created by sunsai on 2016/3/22 - 10:09.
 */
public class CAjaxModifySkuCommand implements Command{
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {

    int serviceId = UserHelper.getServiceID(request);

    String imgUrl = request.getParameter("imgUrl");

    String barCode = request.getParameter("barCode");

    int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuId"),0);

    ProductSkuVO sku = ProductSkuBean.newInstance().getSkuBySkuId(serviceId, skuId);

    if (null != sku) {
      sku.setIMG(imgUrl);
      sku.setBAR_CODE(barCode);
    }

    ProductSkuBean.newInstance().updateProductSku(serviceId, sku);

    return JsonResultUtil.ok();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {
    int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuId"),0);

    if (0 == skuId) {
      validate.addError("出错了，请刷新页面之后重试！");
    }


  }
}
