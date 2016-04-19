package com.xcooper.task.taskcheckitem.web.command;

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
import com.xcooper.task.taskcheckitem.busi.TaskCheckItemBean;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddTaskCheckItemCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        TaskCheckItemBean taskCheckItemBean = new TaskCheckItemBean();

        TaskCheckItemVO taskCheckItemVO = new TaskCheckItemVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        //插入id
        taskCheckItemVO.setID(SeqNumHelper.getNewSeqNum("task_check_item"));
        //taskCheckItemVO.setID(StrUtil.getNotNullIntValue(request.getParameter("id"), 0));

        //检查项名itemName
        taskCheckItemVO.setITEM_NAME(request.getParameter("itemName"));

        //任务id taskId
        taskCheckItemVO.setTASK_ID(StrUtil.getNotNullIntValue(request.getParameter("taskId"), 0));


        try {
            taskCheckItemBean.addTaskCheckItem(taskCheckItemVO);
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
