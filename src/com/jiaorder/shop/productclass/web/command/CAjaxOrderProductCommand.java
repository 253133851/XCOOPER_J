package com.jiaorder.shop.productclass.web.command;

import com.jiaorder.shop.productclass.busi.ProductClassBean;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 交换商品分类的排序command
 */
public class CAjaxOrderProductCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {

    //得到要交换排序的商品id
    int serviceId =  UserHelper.getServiceID(request);
    int oneClassId = StrUtil.getNotNullIntValue(request.getParameter("oneClassId"));
    int otherClassId = StrUtil.getNotNullIntValue(request.getParameter("otherClassId"));

    ProductClassBean bean = ProductClassBean.newInstance();
    boolean result = bean.changeProductClassOrderNum(serviceId, oneClassId, otherClassId);
    if (result) {
      return JsonResultUtil.ok();
    }
    //日志记录
    LogUtil.operLog(LogType.PRODUCT,"改变商品分类顺序",oneClassId+" & "+otherClassId,request);
    return JsonResultUtil.error();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }
}
