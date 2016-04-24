package com.xcooper.comment.web.command;

import com.pabula.common.util.*;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.comment.busi.CommentBean;
import com.xcooper.comment.vo.CommentVO;
import com.xcooper.list.busi.ListBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by 26901 on 2016.4.16.
 */
public class CAjaxAddCommentCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        CommentBean bean = new CommentBean();

        ListBean listBean = new ListBean();


        CommentVO comment = new CommentVO();

        //SeqNumHelper.getNewSeqNum("xxxx")  向VO中插入"comment"表中可用的id
        comment.setCOMMENT_ID(SeqNumHelper.getNewSeqNum("comment"));

        //request.getParameter("xxx")  插入字符串
        comment.setCOMMENT_TITLE(request.getParameter("title"));


        //StrUtil.getNotNullIntValue("")  插入不为null的int
        comment.setIS_DONE(StrUtil.getNotNullIntValue(request.getParameter("isDone"), 0));

        try {
            bean.addComment(comment);
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
