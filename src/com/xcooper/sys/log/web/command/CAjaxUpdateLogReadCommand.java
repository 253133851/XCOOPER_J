package com.xcooper.sys.log.web.command;

import com.pabula.common.db.MysqlDialect;
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
import com.xcooper.project.project.busi.ProjectBean;
import com.xcooper.project.project.vo.ProjectVO;
import com.xcooper.project.projectMember.busi.ProjectMemberBean;
import com.xcooper.project.projectMember.vo.ProjectMemberVO;
import com.xcooper.sys.log.busi.LogBean;
import com.xcooper.sys.log.vo.LogVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxUpdateLogReadCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        LogBean logBean = new LogBean();

        String logIds =request.getParameter("logIds");

        String[] logIdsArray = logIds.split(".,");

        for (int i = 0; i < logIdsArray.length; i++) {

            LogVO logVO = logBean.getLogByID(StrUtil.getNotNullIntValue(logIdsArray[i],0));

            //设置log表中的remark1字段 为HasRead
            logVO.setREMARK1("HasRead");

            //执行修改
            logBean.modifyLog(logVO);
        }
        //返回ok
        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
