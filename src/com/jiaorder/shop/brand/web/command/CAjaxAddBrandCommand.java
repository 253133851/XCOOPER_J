package com.jiaorder.shop.brand.web.command;

import com.jiaorder.shop.brand.busi.BrandBean;
import com.jiaorder.shop.brand.vo.BrandVO;
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
 * 新增品牌command
 */
public class CAjaxAddBrandCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        BrandBean bean = BrandBean.newInstance();

        //得到品牌数据
        int serviceId =  UserHelper.getServiceID(request);
        int BrandId = StrUtil.getNotNullIntValue(request.getParameter("brandId"), 0);
        String Brand = request.getParameter("brand");

        //创建品牌vo对象
        BrandVO vo = new BrandVO();
        vo.setBRAND(Brand);
        vo.setBRAND_ID(BrandId);
        vo.setSERVICE_ID(serviceId);
        if (bean.addBrand(serviceId, vo)) {
            return JsonResultUtil.ok();
        }

        //日志记录
        LogUtil.operLog(LogType.OTHER,"新增品牌",BrandId,request);

        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
