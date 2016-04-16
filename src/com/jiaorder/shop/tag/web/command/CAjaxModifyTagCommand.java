package com.jiaorder.shop.tag.web.command;

import com.jiaorder.shop.tag.busi.TagBean;
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
 * 修改标签command
 */
public class CAjaxModifyTagCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        TagBean bean = TagBean.newInstance();

        //接收标签相关数据
        int serviceId =  UserHelper.getServiceID(request);
        int TagId = StrUtil.getNotNullIntValue(request.getParameter("tagid"), 0);
        String Tag = request.getParameter("tag");

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"修改标签",TagId+"  name: "+Tag,request);

        if (bean.modifyTag(serviceId, TagId, Tag)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
