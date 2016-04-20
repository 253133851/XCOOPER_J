package com.xcooper.list.web.command;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddListCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        ListBean listbean = new ListBean();

        ListVO list = new ListVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        list.setLIST_ID(SeqNumHelper.getNewSeqNum("list"));
        //list.setLIST_ID(StrUtil.getNotNullIntValue(request.getParameter("listId"),0));

        //清单名listName
        list.setLIST_NAME(request.getParameter("listName"));

        //项目id projectId
        list.setPROJECT_ID(projectId);

        //排序号 orderNum 默认设置为0
        list.setORDER_NUM(StrUtil.getNotNullIntValue(request.getParameter("orderNum"), 0));


        try {
            listbean.addList(list);
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

    //项目id
    int projectId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        projectId = StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0);
        if (projectId == 0) {
            validate.addError("项目id错误");
        }
    }
}
