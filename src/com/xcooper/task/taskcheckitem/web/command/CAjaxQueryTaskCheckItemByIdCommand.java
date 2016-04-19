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
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxQueryTaskCheckItemByIdCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();
        int taskId = StrUtil.getNotNullIntValue(request.getParameter("taskId"), 0);

        try {
            Collection list = taskCheckItemBean.getTaskCheckItemColl("select * from task_check_item where task_id = " + taskId);

            //返回查询的所有json数据
            //如果查询不到数据则返回ok
            if (list.size() <= 0) {
                return JsonResultUtil.instance().ok();
            }
            return JsonResultUtil.instance().addData(list).json();
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
