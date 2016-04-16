package com.jiaorder.sys.member.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.member.busi.MemberBean;
import com.jiaorder.sys.member.vo.MemberVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 得到用户分类(归属区)集合command
 */
public class CAjaxGetMemberCollCommand implements Command {

    int serviceId,pageSize,pageIndex,memberLevel,memberState;
    String memberOrder,membername;

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberVO filterMember = new MemberVO();
        filterMember.setMEMBER_LEVEL_ID(memberLevel);
        filterMember.setSTATE(memberState);
        filterMember.setPageInfo(pageSize,pageIndex);
        filterMember.setUSER_NAME(membername);

        //加入page格式
        MemberBean bean = MemberBean.newInstance();
        Page<MemberVO> page = new Page<>();
        page.setItems(bean.getMemberColl(serviceId, memberOrder, filterMember));
        page.setPageSize(filterMember.getPageSize());
        page.setCurrentPage(filterMember.getPageIndex());
        page.setTotalCount(bean.getMemberColl_count(serviceId,filterMember));
        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"获取目标条件下的用户","",request);
        return JsonResultUtil.instance().addData(page).json();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

        serviceId =  UserHelper.getServiceID(request);
        pageSize = StrUtil.getNotNullIntValue(request.getParameter("pageSize"), 1);
        pageIndex = StrUtil.getNotNullIntValue(request.getParameter("pageIndex"), 1);
        memberLevel = StrUtil.getNotNullIntValue(request.getParameter("memberLevel"), -100);
        memberState = StrUtil.getNotNullIntValue(request.getParameter("memberState"), -100);

        membername =request.getParameter("memberName");
        memberOrder = request.getParameter("memberOrder");
        if (null == memberOrder || ("").equals(memberOrder)) {
            memberOrder = "REG_DATE DESC";
        }

    }
}
