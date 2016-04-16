package com.jiaorder.shop.productclass.web.command;

import com.jiaorder.shop.productclass.busi.ProductClassBean;
import com.jiaorder.shop.productclass.vo.ProductClassVO;
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
 * 新增商品分类command
 */
public class CAjaxAddProductClassCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {

    ProductClassBean bean = ProductClassBean.newInstance();

    //接收分类相关数据
    int serviceId =  UserHelper.getServiceID(request);
    int parentClassId = StrUtil.getNotNullIntValue(request.getParameter("parentClassId"), 0);
    String className = request.getParameter("className");

    //创建分类vo 并添加
    ProductClassVO vo = new ProductClassVO();
    vo.setPARENT_CLASS_ID(parentClassId);
    vo.setCLASS_NAME(className);
    vo.setSERVICE_ID(serviceId);
    if (bean.addProductClass(vo)) {
      return JsonResultUtil.ok();
    }
    //日志记录
    LogUtil.operLog(LogType.PRODUCT,"新增商品分类",parentClassId,request);
    return JsonResultUtil.error();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }
}
