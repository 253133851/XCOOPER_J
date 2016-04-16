package com.jiaorder.shop.product.web.command;

import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.logger.Logger;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunsai on 2016/3/15.
 */
public class CAjaxGetProductSpuCodeCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {
    int serviceId = UserHelper.getServiceID(request);
    int i = 0;
    String spu = null;
    do {
      i++;
      spu = ProductBean.newInstance().createProductSpuCode(serviceId);
      Logger.d(spu);
    } while (i < 5 && spu == null);

    if (StrUtil.isNotNull(spu)) {
      return JsonResultUtil.instance().addData(spu).json();
    } else {
      return JsonResultUtil.error();
    }
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

  }
}
