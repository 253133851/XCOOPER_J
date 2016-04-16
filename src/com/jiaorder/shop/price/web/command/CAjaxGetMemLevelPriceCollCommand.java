package com.jiaorder.shop.price.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.price.busi.ProductPriceMemberLevelBean;
import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
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
import java.util.List;

/**
 * Created by sunsai on 2016/3/22 - 14:17.
 */
public class CAjaxGetMemLevelPriceCollCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {

    int serviceId = UserHelper.getServiceID(request);

    int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuId"),0);

    List<ProductPriceMemberLevelVO>
        productPriceMemberLevelVOs = ProductPriceMemberLevelBean.newInstance().getProductPriceMemberLevelCollBySkuId(serviceId, skuId);

    Page<ProductPriceMemberLevelVO> page = new Page<>();

    page.setItems(productPriceMemberLevelVOs);

    return JsonResultUtil.instance().addData(page).json();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {
    int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuId"),0);

    if (skuId == 0) {
      validate.addError("出错了，请刷新重试！");
    }
  }
}
