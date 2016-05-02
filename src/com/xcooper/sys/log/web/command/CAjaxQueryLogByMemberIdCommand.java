package com.xcooper.sys.log.web.command;

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
import com.xcooper.comment.busi.TopicBean;
import com.xcooper.comment.vo.TopicVO;
import com.xcooper.sys.log.busi.LogBean;
import com.xcooper.sys.log.vo.LogVO;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 通知!
 * Created by zdk on 2016.5.02.
 */
public class CAjaxQueryLogByMemberIdCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        LogBean logBean = new LogBean();

        TaskBean taskBean = new TaskBean();

        TopicBean topicBean =  new TopicBean();

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        //我负责的任务 有关通知 (完成了,回复了,删除了,编辑了)

        //我创建的任务 有关通知

        //我关注的任务 有关通知

        //返回 我负责的任务
        List<TaskVO> myTaskList = (List<TaskVO>) taskBean.getTaskColl
                ("select * from task where exe_id = " + memberId);
        //返回 我创建的任务
        List<TaskVO> myCreateTaskList = (List<TaskVO>) taskBean.getTaskColl("select * from task where create_id =" + memberId);

        //返回 我关注的任务
        List<TaskVO> myFocusTaskList = (List<TaskVO>) taskBean.getTaskCollWithLeftJoin("select * from task left join member_task " +
                "on task.task_id = member_task.task_id where member_task.member_id = " + memberId + " and member_task.is_focus = 1");

        //返回 表COMMENT的所有数据
        String taskIdsList = "";
        for (int i = 0; i < myTaskList.size(); i++) {
            taskIdsList = taskIdsList + "," + myTaskList.get(i).getTASK_ID();
        }
        for (int i = 0; i < myCreateTaskList.size(); i++) {
            taskIdsList = taskIdsList + "," + myCreateTaskList.get(i).getTASK_ID();
        }
        for (int i = 0; i < myFocusTaskList.size(); i++) {
            taskIdsList = taskIdsList + "," + myFocusTaskList.get(i).getTASK_ID();
        }

        //我创建话题   有关通知 (回复了)

        //返回 我创建的话题
        List<TopicVO> myCreateTopicList = (List<TopicVO>) topicBean.getTopicColl("select * from topic where create_id = "+memberId);

        try {

            //查询出所有相关任务的 日志 通知
            Collection list1 = logBean.getLogColl("select * from log where log_type = +" + LogType.TASK + "and target_id in (" + taskIdsList.substring(1) + ")");

            //查询出所有话题相关的 日志 通知
            Collection list2 = logBean.getLogColl("select * from log");

            //返回查询的所有json数据

            return JsonResultUtil.instance().addData(list1).json();
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
