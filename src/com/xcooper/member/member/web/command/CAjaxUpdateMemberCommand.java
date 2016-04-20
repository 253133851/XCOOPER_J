package com.xcooper.member.member.web.command;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateMemberCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        ListBean bean = new ListBean();

        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        ListVO list = bean.getListByID(id);

        //修改 清单名 listName
        list.setLIST_NAME(request.getParameter("listName"));

        //修改 排序值 orderNum
        list.setORDER_NUM(StrUtil.getNotNullIntValue(request.getParameter("orderNum"),0));


//        list.setADD_DATETIME(Timestamp.valueOf(request.getParameter("截止时间")));

        bean.modifyList(list);

        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
