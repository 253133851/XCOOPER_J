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
import com.xcooper.comment.busi.TopicBean;
import com.xcooper.comment.vo.CommentVO;
import com.xcooper.comment.vo.TopicVO;
import com.xcooper.sys.log.web.command.LogType;
import com.xcooper.sys.log.web.command.LogUtil;
import com.xcooper.sys.log.web.command.OperaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.5.2.
 */
public class CAjaxUpdateTopicCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TopicBean topicBean = new TopicBean();

        int topicId = StrUtil.getNotNullIntValue(request.getParameter("topicId"), 0);

        TopicVO topicVO = topicBean.getTopicByID(topicId);

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        //话题名称 title
        if (null != request.getParameter("title") && "" != request.getParameter("title")) {
            topicVO.setTITLE(request.getParameter("title"));
        }

        //话题描述
        if (null != request.getParameter("describes")) {
            topicVO.setDESCRIBES(request.getParameter("describes"));
        }

        //添加日志
        LogUtil.operaLog(memberId, OperaType.EDIT, LogType.TOPIC, topicId, topicVO.getTITLE());

        try {
            topicBean.modifyTopic(topicVO);
            return JsonResultUtil.instance().addData(topicVO).json();
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
