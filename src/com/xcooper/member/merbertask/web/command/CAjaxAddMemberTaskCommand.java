package com.xcooper.member.merbertask.web.command;

import com.pabula.common.db.MysqlDialect;
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
        memberTaskVO.setMEMBER_ID(StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0));
        //添加 taskId
        memberTaskVO.setTASK_ID(StrUtil.getNotNullIntValue(request.getParameter("taskId"), 0));
        //添加是否关注 isFocus
        int isfocus = StrUtil.getNotNullIntValue(request.getParameter("isFocus"), 0);
        if (isfocus != 1) {
            MysqlDialect.deleteColl("delete from member_task where member_id = " + memberTaskVO.getMEMBER_ID() + " and task_id = " + memberTaskVO.getTASK_ID());
        } else {
            memberTaskVO.setIS_FOCUS(StrUtil.getNotNullIntValue(request.getParameter("isFocus"), 0));
        }

        try {
            memberTaskBean.addMemberTask(memberTaskVO);
            return JsonResultUtil.instance().ok();
        } catch (DataAccessException e) {
            return JsonResultUtil.instance().
                    addMsg(e.getMessage())
                    .addCode(JsonResultUtil.ERROR).json();
        }

    }


    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
