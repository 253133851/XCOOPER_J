package com.xcooper.task.taskcheckitem.web.command;

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
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateTaskCheckItemCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();

        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        TaskCheckItemVO taskCheckItemVO = taskCheckItemBean.getTaskCheckItemByID(id);

        //修改检查项名 itemName
        taskCheckItemVO.setITEM_NAME(request.getParameter("itemName"));

        //修改检查项完成状态 isDown
        taskCheckItemVO.setIS_DONE(StrUtil.getNotNullIntValue(request.getParameter("isDown"), 0));

        taskCheckItemBean.modifyTaskCheckItem(taskCheckItemVO);
        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
