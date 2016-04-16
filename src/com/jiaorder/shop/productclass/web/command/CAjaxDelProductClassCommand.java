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
 *删除商品分类
 */
public class CAjaxDelProductClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        ProductClassBean bean = ProductClassBean.newInstance();

        //要删除的分类的id
        int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), 0);
        int serviceId =  UserHelper.getServiceID(request);

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"删除商品分类",classId,request);

        if (classId > 0) {
            //根据返回值判断有无子类 有的话不能删除
            boolean result = bean.delProductClassAndChildsByClassId(serviceId, classId);
            if (result) {

                return JsonResultUtil.ok();
            } else {
                return JsonResultUtil.instance()
                        .addCode(JsonResultUtil.ERROR)
                        .addMsg("该分类下还有子分类，不能删除！")
                        .json();
            }
        } else {
            //没有正常的得到id
            return JsonResultUtil.instance()
                    .addCode(JsonResultUtil.ERROR)
                    .addMsg("缺少参数")
                    .json();
        }
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
