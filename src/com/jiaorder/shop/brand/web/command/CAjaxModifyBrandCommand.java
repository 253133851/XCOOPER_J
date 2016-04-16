package com.jiaorder.shop.brand.web.command;

import com.jiaorder.shop.brand.busi.BrandBean;
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
 * 修改品牌command
 */
public class CAjaxModifyBrandCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        BrandBean bean = BrandBean.newInstance();

        //得到品牌相关参数
        int serviceId =  UserHelper.getServiceID(request);
        int BrandId = StrUtil.getNotNullIntValue(request.getParameter("brandid"), 0);
        String Brand = request.getParameter("brand");

        if (bean.modifyBrand(serviceId, BrandId, Brand)) {
            return JsonResultUtil.ok();
        }
        //日志记录
        LogUtil.operLog(LogType.OTHER,"修改单位",Brand,request);
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
