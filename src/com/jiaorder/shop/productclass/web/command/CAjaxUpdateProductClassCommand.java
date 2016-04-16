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
 * 更新商品分类command
 */
public class CAjaxUpdateProductClassCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //接收分类相关数据
        int serviceId =  UserHelper.getServiceID(request);
        int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), 0);
        String className = request.getParameter("className");
        int parentClassId = StrUtil.getNotNullIntValue(request.getParameter("parentClassId"), 0);

        ProductClassBean bean = ProductClassBean.newInstance();
        boolean result = bean.updateProductClass(serviceId, classId, parentClassId, className);
        if (result) {
            return JsonResultUtil.ok();
        }
        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"更新商品分类",classId+"  name: "+className,request);
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
