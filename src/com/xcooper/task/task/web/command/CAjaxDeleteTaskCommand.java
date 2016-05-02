package com.xcooper.task.task.web.command;

import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.comment.busi.CommentBean;
import com.xcooper.list.busi.ListBean;
import com.xcooper.member.merbertask.busi.MemberTaskBean;
import com.xcooper.sys.log.web.command.LogType;
import com.xcooper.sys.log.web.command.LogUtil;
import com.xcooper.sys.log.web.command.OperaType;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxDeleteTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        TaskBean taskBean = new TaskBean();

        //删除检查项记录
        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();

        //成员任务表记录
        MemberTaskBean memberTaskBean = new MemberTaskBean();

        //删除讨论记录
        CommentBean commentBean = new CommentBean();

        TaskVO taskVO = taskBean.getTaskByID(id);

        //添加日志
        LogUtil.operaLog(memberId, OperaType.Delete, LogType.TASK,id,taskVO.getTASK_NAME());

        try {
            //删除该id的记录
            taskBean.delTask(id);

            taskCheckItemBean.delTaskCheckItemByTaskId(id);

            memberTaskBean.delMemberTaskByTaskId(id);
            //删除任务讨论 type:1
            commentBean.delCommentByAimId(id,1);

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
