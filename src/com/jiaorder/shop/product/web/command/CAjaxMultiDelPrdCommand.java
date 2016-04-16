package com.jiaorder.shop.product.web.command;

import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 批量删除 Created by sunsai on 2016/3/14.
 */
public class CAjaxMultiDelPrdCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {
    String type = request.getParameter("type");
    int serviceId = UserHelper.getServiceID(request);
    String ids = request.getParameter("ids");

    try {
      if ("spu".equals(type)) {//批量 或 单独 对spu 置为删除状态
        ProductBean.newInstance().multiDelProductByIds(serviceId, ids);
      } else if ("sku".equals(type)) {
        ProductSkuBean.newInstance().multiDelSkuBySkuIds(serviceId, ids);
      }
    } catch (DataAccessException e) {
      e.printStackTrace();
      return JsonResultUtil.error();
    }
    return JsonResultUtil.ok();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

    String type = request.getParameter("type");
    String ids = request.getParameter("ids");

    if (StrUtil.isNull(type)) {
      validate.addError("参数不完整");
    }

    if (StrUtil.isNull(ids)) {
      validate.addError("参数不完整");
    }
  }
}
