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
 * 删除标签command
 */
public class CAjaxDelTagCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        TagBean bean = TagBean.newInstance();

        //得到要删除的标签的id
        int tagId = StrUtil.getNotNullIntValue(request.getParameter("tagId"), 0);
        int serviceId =  UserHelper.getServiceID(request);

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"删除标签",tagId,request);

        if (tagId > 0) {
            //删除成功返回ok码
            boolean result = bean.delTag(serviceId, tagId);
            if (result) {
                return JsonResultUtil.ok();
            }
        } else {
            //没有得到正确的id
            JsonResultUtil.instance()
                    .addCode(JsonResultUtil.ERROR)
                    .addMsg("缺少必要的参数:")
                    .json();
        }

        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
