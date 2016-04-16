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
 *
 *  批量上下架
 * Created by sunsai on 2016/3/14.
 */
public class CAjaxMultiChangePrdSaleCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {
    try {

      String type = request.getParameter("type");
      int serviceId = UserHelper.getServiceID(request);
      String ids = request.getParameter("ids");
      int saleState = StrUtil.getNotNullIntValue(request.getParameter("saleState"), ProductBean.SALE_STATE_ALL);


      if ("spu".equals(type)) {//批量 或 单独 对spu进行上下架
        ProductBean.newInstance().multiChangeSaleStateByIds(serviceId, ids, saleState);
      } else if ("sku".equals(type)){//批量 或 单独 对sku进行上下架
        ProductSkuBean.newInstance().multiChangeSaleStateBySkuIds(serviceId, ids, saleState);
      }

    } catch (DataAccessException e) {
      e.printStackTrace();
      return JsonResultUtil.instance().addCode(JsonResultUtil.ERROR)
          .addMsg("数据库操作错误！请稍后在试").json();
    }
    return JsonResultUtil.ok();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {
    int saleState = StrUtil.getNotNullIntValue(request.getParameter("saleState"), ProductBean.SALE_STATE_ALL);
    String ids = request.getParameter("ids");

    if (StrUtil.isNull(ids)) {
      validate.addError("id为空");
    }

    if (saleState != ProductBean.SALE_STATE_NO && saleState != ProductBean.SALE_STATE_YES) {
      validate.addError("上下架状态错误");
    }
  }
}
