package com.xcooper.tomato.web.command;

import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.tomato.busi.TomatoBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxQueryTomatoCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        TomatoBean tomatoBean = new TomatoBean();

        try {

            //返回 表PROJECT的所有数据
            Collection tomatoColl = tomatoBean.getTomatoColl("select * from tomato");

            //返回查询的所有json数据

            return JsonResultUtil.instance().addData(tomatoColl).json();
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
