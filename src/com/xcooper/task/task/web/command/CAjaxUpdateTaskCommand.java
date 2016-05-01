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
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collection;

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
        if(null!=request.getParameter("taskName")&& !("").equals(request.getParameter("taskName"))) {
            taskVO.setTASK_NAME(request.getParameter("taskName"));
        }
        //修改 截止时间 endDatetime
        if (null != request.getParameter("endDatetime") && !("").equals(request.getParameter("endDatetime"))) {
            taskVO.setEND_DATETIME(Timestamp.valueOf(request.getParameter("endDatetime")));
        }

        //修改 任务描述 taskInfo
        if(null!=request.getParameter("taskInfo")) {
            taskVO.setTASK_INFO(request.getParameter("taskInfo"));
        }
        //修改执行人 exeId
        if(null!=request.getParameter("exeId")) {
            taskVO.setEXE_ID(StrUtil.getNotNullIntValue(request.getParameter("exeId"), 0));
        }
        //修改所属清单 listId
        if(null!=request.getParameter("listId")) {
            taskVO.setLIST_ID(StrUtil.getNotNullIntValue(request.getParameter("listId"), 0));
        }
        //修改所属项目 projectId
        if(null!=request.getParameter("projectId")&& !("").equals(request.getParameter("projectId"))) {
            taskVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0));
        }
        //修改检查项
        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();
        TaskCheckItemVO taskCheckItemVO = new TaskCheckItemVO();

        String itemNames = request.getParameter("itemNames");
        String itemIsDones = request.getParameter("itemIsDones");

        String[] itemNamesArray = itemNames.split(".,");
        String[] itemIsDonesArray = itemIsDones.split(".,");

        MysqlDialect.deleteColl("delete from task_check_item where task_id = " + taskVO.getTASK_ID());

        for (int i = 0; i <itemNamesArray.length ; i++) {

            taskCheckItemVO.setID(SeqNumHelper.getNewSeqNum("task_check_item"));

            taskCheckItemVO.setITEM_NAME(itemNamesArray[i]);

            taskCheckItemVO.setTASK_ID(taskVO.getTASK_ID());

            taskCheckItemVO.setIS_DONE(StrUtil.getNotNullIntValue(itemIsDonesArray[i],0));

            taskCheckItemBean.addTaskCheckItem(taskCheckItemVO);
        }

        //执行修改
        taskBean.modifyTask(taskVO);

        //返回ok
        Collection taskCheckItemList = taskCheckItemBean.getTaskCheckItemColl("select * from task_check_item where task_id = "+ taskVO.getTASK_ID());

        return JsonResultUtil.instance().addData(taskVO).addExtraData(new Object[]{taskCheckItemList}).json();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
