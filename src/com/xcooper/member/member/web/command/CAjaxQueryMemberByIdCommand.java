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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxQueryMemberByIdCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        ListBean bean = new ListBean();

        int projectId = StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0);

        if (projectId == 0) {
            return JsonResultUtil.error();
        }


        try {
            Collection list = bean.getListColl("select * from list where project_id = " + projectId);

            //返回查询的所有json数据
            //判断如果查询不到数据(list.size<=0),则报错
            if (list.size() <= 0) {
                return JsonResultUtil.instance().
                        addMsg("该projectId下找不到内容").addData("projectId=" + projectId)
                        .addCode(JsonResultUtil.ERROR).json();
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
