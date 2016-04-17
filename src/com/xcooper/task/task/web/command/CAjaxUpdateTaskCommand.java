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
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskBean taskBean = new TaskBean();

        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        TaskVO taskVO = taskBean.getTaskByID(id);

        //修改 任务名 taskName
        taskVO.setTASK_NAME(request.getParameter("taskName"));

        //修改 截止时间 endDatetime
        if (null != request.getParameter("endDatetime") && !("").equals(request.getParameter("endDatetime"))) {
            taskVO.setEND_DATETIME(Timestamp.valueOf(request.getParameter("endDatetime")));
        }

        //修改 任务描述 taskInfo
        taskVO.setTASK_INFO(request.getParameter("taskInfo"));

        //修改执行人 exeId
        taskVO.setEXE_ID(StrUtil.getNotNullIntValue(request.getParameter("exeId"),0));

        //修改所属清单 listId
        taskVO.setLIST_ID(StrUtil.getNotNullIntValue(request.getParameter("listId"),0));

        //修改所属项目 projectId
        taskVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"),0));

        //执行修改
        taskBean.modifyTask(taskVO);

        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
