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
 *删除品牌command
 */
public class CAjaxDelBrandCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        BrandBean bean = BrandBean.newInstance();

        //得到参数
        int brandId = StrUtil.getNotNullIntValue(request.getParameter("brandId"), 0);
        int serviceId =  UserHelper.getServiceID(request);
        if (brandId > 0) {
            //得到ID 执行删除语句
            boolean result = bean.delBrand(serviceId, brandId);
            if (result) {
                return JsonResultUtil.ok();
            }
        } else {
            //未得到正确的品牌ID
            JsonResultUtil.instance()
                    .addCode(JsonResultUtil.ERROR)
                    .addMsg("缺少必要的参数:")
                    .json();
        }

        //日志记录
        LogUtil.operLog(LogType.OTHER,"删除品牌",brandId,request);
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
