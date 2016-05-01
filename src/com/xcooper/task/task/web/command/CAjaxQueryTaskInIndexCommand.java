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
import com.xcooper.member.member.busi.MemberBean;
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.sys.user.user.busi.UserBean;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * Created by zdk on 2016.4.17.
 * 在任务主页显示是调用的接口,包括我负责的,我发起的,我关注的
 */
public class CAjaxQueryTaskInIndexCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskBean taskBean = new TaskBean();
        ProjectBean projectBean = new ProjectBean();

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        try {
            //返回 我负责的任务
            List<TaskVO> myTaskList = (List<TaskVO>) taskBean.getTaskColl
                    ("select * from task where exe_id = " + memberId);
            //返回 我创建的任务
            List<TaskVO> myCreateTaskList = (List<TaskVO>) taskBean.getTaskColl("select * from task where create_id =" + memberId);

            //返回 我关注的任务
            List<TaskVO> myFocusTaskList = (List<TaskVO>) taskBean.getTaskCollWithLeftJoin("select * from task left join member_task " +
                    "on task.task_id = member_task.task_id where member_task.member_id = " + memberId + " and member_task.is_focus = 1");

            //返回 表PROJECT的所有数据
            Collection projectList = projectBean.getProjectColl("select * from project");

            //返回 表USER的所有数据
            Collection memberList = new MemberBean().getMemberColl("select * from  member ");

            //返回 表COMMENT的所有数据
            String taskIdsList="";
            for (int i = 0; i <myTaskList.size() ; i++) {
                taskIdsList=taskIdsList+","+myTaskList.get(i).getTASK_ID();
            }
            for (int i = 0; i <myCreateTaskList.size() ; i++) {
                taskIdsList=taskIdsList+","+myCreateTaskList.get(i).getTASK_ID();
            }
            for (int i = 0; i < myFocusTaskList.size(); i++) {
                taskIdsList=taskIdsList+","+myFocusTaskList.get(i).getTASK_ID();
            }
            Collection commentList = new CommentBean().getCommentColl("select * from comment where aim_id in (" + taskIdsList.substring(1) + ") and type = 1");

            // 返回 这些任务的检查项
            Collection taskCheckItemList = new TaskCheckItemBean().getTaskCheckItemColl("select * from task_check_item where task_id in("+ taskIdsList.substring(1)+")");


            //返回查询的所有json数据
            if (myTaskList.size() <= 0) {
                return JsonResultUtil.instance().
                        addMsg("找不到内容")
                        .addCode(JsonResultUtil.ERROR).json();
            }

            return JsonResultUtil.instance().addExtraData(new Object[]{myTaskList, myCreateTaskList, myFocusTaskList, projectList, memberList, commentList,taskCheckItemList}).json();

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
