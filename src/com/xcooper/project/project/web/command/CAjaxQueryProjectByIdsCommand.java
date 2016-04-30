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
import com.xcooper.member.member.busi.MemberBean;
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.task.task.busi.TaskBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxQueryProjectByIdsCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskBean taskBean = new TaskBean();

        MemberBean memberBean = new MemberBean();

        String projectIds = request.getParameter("projectIds");

        String[] projectIdsArray = projectIds.split(".,");

        try {
            Collection taskList = taskBean.getTaskColl("select * from task where project_id in (" + projectIds +")");



            //返回查询的所有json数据

            return JsonResultUtil.instance().addData(taskList).addExtraData(new Object[]{"所有在projectIds里面的任务的讨论数据"}).json();
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
