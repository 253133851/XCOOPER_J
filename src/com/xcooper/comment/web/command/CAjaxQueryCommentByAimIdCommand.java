package com.xcooper.comment.web.command;

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
import com.xcooper.comment.busi.CommentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by 26901 on 2016.4.16.
 */
public class CAjaxQueryCommentByAimIdCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        int aimId = StrUtil.getNotNullIntValue(request.getParameter("aimId"),0);

        CommentBean commentBean = new CommentBean();

        if(aimId==0){
            return JsonResultUtil.error();
        }
        Collection list = commentBean.getCommentColl("select * from comment where aim_id = "+ aimId );

        return JsonResultUtil.instance().addData(list).json();

    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}