package com.jiaorder.sys.memberlevel.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberlevel.busi.MemberLevelBean;
import com.jiaorder.sys.memberlevel.vo.MemberLevelVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 得到客户级别command
 */
public class CAjaxGetMemberLevelCollCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        int serviceId = UserHelper.getServiceID(request);

        //加入page格式
        MemberLevelBean bean = MemberLevelBean.newInstance();
        List<MemberLevelVO> list = bean.getMemberLevelColl(serviceId);
        Page<MemberLevelVO> page = new Page<>();
        page.setItems(list);
        String json = JsonResultUtil.instance().addData(page).json();
        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"获取客户级别","",request);
        return json;

    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
