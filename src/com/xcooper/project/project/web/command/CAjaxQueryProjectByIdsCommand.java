package com.xcooper.project.project.web.command;

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
import com.xcooper.comment.busi.TopicBean;
import com.xcooper.member.member.busi.MemberBean;
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.project.projectMember.busi.ProjectMemberBean;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxQueryProjectByIdsCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskBean taskBean = new TaskBean();

        MemberBean memberBean = new MemberBean();

        String projectIds = request.getParameter("projectIds");

        String[] projectIdsArray = projectIds.split(",");

        try {
            //List<TaskVO> 是 Collection的子集

            List<TaskVO> taskList = (List<TaskVO>) taskBean.getTaskColl("select * from task where project_id in (" + projectIds + ")");

            String commentTaskIds = "";

            for (int i = 0; i < taskList.size(); i++) {
                commentTaskIds = commentTaskIds + "," + taskList.get(i).getTASK_ID();
            }
            //.substring(1) 从第二字符开始切割
            Collection commentTaskList = new CommentBean().getCommentColl("select * from comment where aim_id in (" + commentTaskIds.substring(1) + ") and type = 1");

            Collection commentProjectList = new CommentBean().getCommentColl("select * from comment where aim_id in (" + projectIds + ") and type = 2");

            // 返回 这些任务的检查项
            Collection taskCheckItemList = new TaskCheckItemBean().getTaskCheckItemColl("select * from task_check_item where task_id in(" + commentTaskIds.substring(1) + ")");

            // 返回 这些任务的检查项
            Collection projectMemberColl = new ProjectMemberBean().getProjectMemberColl("select * from project_member where PEOJECT_ID in(" + projectIds + ")");

            Collection topicColl = new TopicBean().getTopicColl(" select * from topic where project_id in (" + projectIds + ")");

            //返回查询的所有json数据
            return JsonResultUtil.instance().addData(taskList).addExtraData(new Object[]{commentTaskList, commentProjectList, taskCheckItemList, projectMemberColl, topicColl}).json();
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
