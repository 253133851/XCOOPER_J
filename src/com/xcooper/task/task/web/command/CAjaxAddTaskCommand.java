package com.xcooper.task.task.web.command;

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
import com.xcooper.sys.log.web.command.LogType;
import com.xcooper.sys.log.web.command.LogUtil;
import com.xcooper.sys.log.web.command.OperaType;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        TaskBean taskBean = new TaskBean();

        TaskVO taskVO = new TaskVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 向VO对象中插入"xxxx"表中可用ID
        taskVO.setTASK_ID(SeqNumHelper.getNewSeqNum("task"));

        taskVO.setLIST_ID(StrUtil.getNotNullIntValue(request.getParameter("listId"), 0));

        taskVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0));

        taskVO.setCREATE_ID(StrUtil.getNotNullIntValue(request.getParameter("createId"), 0));

        taskVO.setEXE_ID(StrUtil.getNotNullIntValue(request.getParameter("exeId"), 0));

        //设置taskName任务名称
        taskVO.setTASK_NAME(request.getParameter("taskName"));

        //设置截止日期(截止时间为空就不设置)
        if (null != request.getParameter("endDatetime") && !("").equals(request.getParameter("endDatetime"))) {
            taskVO.setEND_DATETIME(Timestamp.valueOf(request.getParameter("endDatetime")));
        }

        //设置任务描述
        taskVO.setTASK_INFO(request.getParameter("taskInfo"));

        //添加操作日志
        LogUtil.operaLog(taskVO.getCREATE_ID(), OperaType.ADD, LogType.TASK,taskVO.getTASK_ID(),taskVO.getTASK_NAME());

        try {
            taskBean.addTask(taskVO);
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


    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
