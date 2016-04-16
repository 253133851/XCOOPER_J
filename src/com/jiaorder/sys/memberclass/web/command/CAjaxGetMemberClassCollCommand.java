package com.jiaorder.sys.memberclass.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberclass.busi.MemberClassBean;
import com.jiaorder.sys.memberclass.vo.MemberClassVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 得到用户分类(归属区)集合command
 */
public class CAjaxGetMemberClassCollCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        int serviceId = UserHelper.getServiceID(request);

        //加入page格式
        MemberClassBean bean = MemberClassBean.newInstance();
        List<MemberClassVO> list = bean.getMemberClassColl(serviceId);
        Page<MemberClassVO> page = new Page<>();
        page.setItems(list);
        String json = JsonResultUtil.instance().addData(page).json();

        //日志记录
        LogUtil.operLog(LogType.MEMEBER, "获取客户归属区", "", request);
        return json;
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
