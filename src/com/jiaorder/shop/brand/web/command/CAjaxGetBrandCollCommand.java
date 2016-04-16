package com.jiaorder.shop.brand.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.brand.busi.BrandBean;
import com.jiaorder.shop.brand.vo.BrandVO;
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
 * 获取所有集合command
 */
public class CAjaxGetBrandCollCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        int serviceId =  UserHelper.getServiceID(request);
        BrandBean bean = BrandBean.newInstance();

        //加入page格式
        List<BrandVO> list = bean.getBrandColl(serviceId);
        Page<BrandVO> page = new Page<>();
        page.setItems(list);
        String json = JsonResultUtil.instance().addData(page).json();

        //日志记录
        LogUtil.operLog(LogType.OTHER,"获取单位集合","",request);
        return json;
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
