package com.jiaorder.shop.productintro.web.command;

import com.jiaorder.shop.productintro.busi.ProductIntroBean;
import com.jiaorder.shop.productintro.vo.ProductIntroVO;
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
 * Created by sunsai on 2016/3/23 - 17:08.
 */
public class CAjaxGetPrdIntroCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {

    int serviceId = UserHelper.getServiceID(request);

    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"));

    ProductIntroVO intro = ProductIntroBean.newInstance().getProductIntroByProductId(serviceId, prdId);

    if (null != intro) {
     return JsonResultUtil.instance().addData(intro).json();
    } else {
      return JsonResultUtil.error();
    }

  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {
    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"),0);

    if (0 == prdId) {
      validate.addError("出错了，请刷新重试");
    }

  }
}
