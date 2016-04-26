package com.xcooper.tomato.web.command;

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
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.tomato.busi.TomatoBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxDeleteTomatoCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        int tomatoId = StrUtil.getNotNullIntValue(request.getParameter("tomatoId"), 0);

        TomatoBean tomatoBean = new TomatoBean();

        try {
            //删除该id的记录
            tomatoBean.delTomato(tomatoId);
            return JsonResultUtil.instance().ok();
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
