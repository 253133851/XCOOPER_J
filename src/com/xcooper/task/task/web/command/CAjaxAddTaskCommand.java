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
import com.xcooper.member.merbertask.busi.MemberTaskBean;
import com.xcooper.member.merbertask.vo.MemberTaskVO;
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
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddTaskCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        TaskBean taskBean = new TaskBean();

        MemberTaskBean memberTaskBean = new MemberTaskBean();

        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();

        TaskVO taskVO = new TaskVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 向VO对象中插入"xxxx"表中可用ID
        taskVO.setTASK_ID(SeqNumHelper.getNewSeqNum("task"));

        // 清单id listId
        taskVO.setLIST_ID(StrUtil.getNotNullIntValue(request.getParameter("listId"), 0));

        //项目id projectId
        taskVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0));

        //创建人id createId
        taskVO.setCREATE_ID(StrUtil.getNotNullIntValue(request.getParameter("createId"), 0));

        //执行人id exeId
        taskVO.setEXE_ID(StrUtil.getNotNullIntValue(request.getParameter("exeId"), 0));

        //任务名称 taskName
        taskVO.setTASK_NAME(request.getParameter("taskName"));

        //设置截止日期(截止时间为空就不设置)
        if (null != request.getParameter("endDatetime") && !("").equals(request.getParameter("endDatetime"))) {
            taskVO.setEND_DATETIME(Timestamp.valueOf(request.getParameter("endDatetime")));
        }

        //设置任务描述 taskInfo
        taskVO.setTASK_INFO(request.getParameter("taskInfo"));

        //添加操作日志
        LogUtil.operaLog(taskVO.getCREATE_ID(), OperaType.ADD, LogType.TASK,taskVO.getTASK_ID(),taskVO.getTASK_NAME());

        //添加关注人 focusIds
        String focusIds = request.getParameter("focusIds");

        String[] focusArray = focusIds.split(".,") ;

        for (int i = 0; i < focusArray.length; i++) {
            MemberTaskVO memberTaskVO = new MemberTaskVO();

            memberTaskVO.setID(SeqNumHelper.getNewSeqNum("member_task"));

            //设置当前任务的id
            memberTaskVO.setTASK_ID(taskVO.getTASK_ID());

            //插入关注人id
            memberTaskVO.setMEMBER_ID(StrUtil.getNotNullIntValue(focusArray[i],0));

            //设置为关注
            memberTaskVO.setIS_FOCUS(1);

            memberTaskBean.addMemberTask(memberTaskVO);
        }
        //添加检查项 itemIds
        String itemNames = request.getParameter("itemNames");

        String[] itemNamesArray = itemNames.split(".,");

        TaskCheckItemVO taskCheckItemVO = new TaskCheckItemVO();

        for (int i = 0; i <itemNamesArray.length ; i++) {


            taskCheckItemVO.setID(SeqNumHelper.getNewSeqNum("task_check_item"));

            taskCheckItemVO.setTASK_ID(taskVO.getTASK_ID());

            //设置检查项名
            taskCheckItemVO.setITEM_NAME(itemNamesArray[i]);

            //设置检查项 为未完成 -1
            taskCheckItemVO.setIS_DONE(-1);

            taskCheckItemBean.addTaskCheckItem(taskCheckItemVO);
        }
        try {
            Collection taskCheckItemList = taskCheckItemBean.getTaskCheckItemColl("select * form task_check_item where task_id = "+ taskVO.getTASK_ID());
            taskBean.addTask(taskVO);
            return JsonResultUtil.instance().addData(taskVO).addExtraData(new Object[]{taskCheckItemList} ).ok();
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
