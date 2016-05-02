package com.xcooper.task.task.web.command;

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
import com.xcooper.sys.log.web.command.LogType;
import com.xcooper.sys.log.web.command.LogUtil;
import com.xcooper.sys.log.web.command.OperaType;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * 设置是否完成了任务 暂时使用is_del字段 1表示完成,-1表示未完成
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateTaskIsDownCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskBean taskBean = new TaskBean();

        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        //获得操作人的memberId
        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        TaskVO taskVO = taskBean.getTaskByID(id);

        //修改 任务名 taskName
        if (null != request.getParameter("taskName") && !("").equals(request.getParameter("taskName"))) {
            taskVO.setTASK_NAME(request.getParameter("taskName"));
        }
        if (0 != StrUtil.getNotNullIntValue(request.getParameter("isDel"), 0)) {
            if (1 == StrUtil.getNotNullIntValue(request.getParameter("isDel"), 0)) {
                taskVO.setIS_DEL(1);
                //操作日志
                LogUtil.operaLog(memberId, OperaType.COMPLETE, LogType.TASK, taskVO.getTASK_ID(), taskVO.getTASK_NAME());

            } else if (1 != StrUtil.getNotNullIntValue(request.getParameter("isDel"), 0)) {
                taskVO.setIS_DEL(-1);
                //操作日志
                LogUtil.operaLog(memberId, OperaType.OPEN, LogType.TASK, taskVO.getTASK_ID(), taskVO.getTASK_NAME());
            }
        }

        //执行修改
        taskBean.modifyTask(taskVO);


        //返回ok

        return JsonResultUtil.instance().addData(taskVO).json();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
