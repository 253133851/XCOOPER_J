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


        CommentBean commentBean = new CommentBean();

        CommentVO commentVO = new CommentVO();

        //SeqNumHelper.getNewSeqNum("xxxx")  向VO中插入"comment"表中可用的id
        commentVO.setCOMMENT_ID(SeqNumHelper.getNewSeqNum("comment"));

        //插入任务/话题id aimId
        commentVO.setAIM_ID(StrUtil.getNotNullIntValue(request.getParameter("aimId"), 0));

        //评论人id commentId
        commentVO.setCOMMENT_MEMBER_ID(StrUtil.getNotNullIntValue(request.getParameter("commentMemberId"), 0));

        //讨论标题 commentTitle
        //request.getParameter("xxx")  插入字符串
        commentVO.setCOMMENT_TITLE(request.getParameter("commentTitle"));

        //评论内容 comment
        commentVO.setCOMMENT(request.getParameter("comment"));

        //类型 type
        commentVO.setTYPE(StrUtil.getNotNullIntValue(request.getParameter("type"), 0));

        //通知目标 targetId
        commentVO.setTARGET_ID(request.getParameter("targetId"));

        //访客是否可见 isShow
        commentVO.setIS_SHOW(StrUtil.getNotNullIntValue(request.getParameter("isShow"), 0));

        //是否结束 isDown
        commentVO.setIS_DONE(StrUtil.getNotNullIntValue(request.getParameter("isDown"), 0));

        //排序值 orderNum
        commentVO.setORDER_NUM(StrUtil.getNotNullIntValue(request.getParameter("orderNum"), 0));

        try {
            commentBean.addComment(commentVO);
            return JsonResultUtil.instance().addData(commentVO.getCOMMENT_ID()).addCode(JsonResultUtil.OK).addMsg("添加成功").json();
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
