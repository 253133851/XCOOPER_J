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
import com.xcooper.comment.vo.CommentVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 26901 on 2016.4.16.
 */
public class CAjaxUpdateCommentCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        CommentBean commentBean = new CommentBean();

        int commentId = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        CommentVO commentVO = commentBean.getCommentByID(commentId);

        commentVO.setCOMMENT_TITLE(request.getParameter("title"));
        //讨论标题 commentTitle
        //request.getParameter("xxx")  插入字符串
        commentVO.setCOMMENT_TITLE(request.getParameter("commentTitle"));

        //评论内容 comment
        commentVO.setCOMMENT(request.getParameter("comment"));

        //通知目标 targetId
        commentVO.setTARGET_ID(request.getParameter("targetId"));

        //访客是否可见 isShow
        commentVO.setIS_SHOW(StrUtil.getNotNullIntValue(request.getParameter("isShow"),0));

        //是否结束 isDown
        commentVO.setIS_DONE(StrUtil.getNotNullIntValue(request.getParameter("isDown"),0));

        //排序值 orderNum
        commentVO.setORDER_NUM(StrUtil.getNotNullIntValue(request.getParameter("orderNum"),0));

        try {
            commentBean.modifyComment(commentVO);
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
