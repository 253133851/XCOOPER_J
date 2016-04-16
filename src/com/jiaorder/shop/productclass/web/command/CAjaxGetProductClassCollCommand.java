package com.jiaorder.shop.productclass.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.productclass.busi.ProductClassBean;
import com.jiaorder.shop.productclass.vo.ProductClassVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 得到商品分类command
 */
public class CAjaxGetProductClassCollCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {

    int serviceId =  UserHelper.getServiceID(request);
    ProductClassBean bean = ProductClassBean.newInstance();

    //加入page格式
    List<ProductClassVO> list = bean.getProductClassColl(serviceId);
    Page<ProductClassVO> page = new Page<>();
    page.setItems(list);
    String json = JsonResultUtil.instance().addData(page).json();
    //日志记录
    LogUtil.operLog(LogType.PRODUCT,"得到商品分类","",request);
    return json;
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }
}
