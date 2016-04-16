package com.jiaorder.shop.unit.web.command;

import com.jiaorder.shop.unit.busi.UnitBean;
import com.jiaorder.shop.unit.vo.UnitVO;
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
 * 新增单位command
 */
public class CAjaxAddUnitCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        UnitBean bean = UnitBean.newInstance();

        //接收单位相关数据
        int serviceId =  UserHelper.getServiceID(request);
        int unitId = StrUtil.getNotNullIntValue(request.getParameter("unitId"), 0);
        String unit = request.getParameter("unit");

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"新增单位",unitId+"  name: "+unit,request);

        //创建单位vo对象并且新增到数据库
        UnitVO vo = new UnitVO();
        vo.setUNIT(unit);
        vo.setUNIT_ID(unitId);
        vo.setSERVICE_ID(serviceId);
        if (bean.addUnit(serviceId,vo)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
