package com.xcooper.member.merbertask.web.command;

import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.member.merbertask.busi.MemberTaskBean;
import com.xcooper.member.merbertask.vo.MemberTaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.22.
 */
public class CAjaxAddMemberTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        MemberTaskBean memberTaskBean = new MemberTaskBean();

        MemberTaskVO memberTaskVO = new MemberTaskVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        memberTaskVO.setID(SeqNumHelper.getNewSeqNum("member_task"));

        //添加 memberId
        memberTaskVO.setMEMBER_ID(StrUtil.getNotNullIntValue(request.getParameter("memberId"),0));

        //添加 taskId
        memberTaskVO.setTASK_ID(StrUtil.getNotNullIntValue(request.getParameter("taskId"),0));

        //添加是否关注 isFocus
        memberTaskVO.setIS_FOCUS(StrUtil.getNotNullIntValue(request.getParameter("isFocus"),0));

        try {
            memberTaskBean.addMemberTask(memberTaskVO);
            return JsonResultUtil.instance().ok();
        } catch (DataAccessException e) {
            return JsonResultUtil.instance().
                    addMsg(e.getMessage())
                    .addCode(JsonResultUtil.ERROR).json();
        }

//        返回error
//        JsonResultUtil.instance().error();
//        返回ok
//        JsonResultUtil.instance().ok();
//        返回带参数的json
//        JsonResultUtil.instance()
//                .addMsg(e.getMessage()).
//                addCode(JsonResultUtil.OK)
//                .addData("xxx")
//                .json();
    }

    //项目id
    int projectId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        projectId = StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0);
        if (projectId == 0) {
            validate.addError("项目id错误");
        }
    }
}
