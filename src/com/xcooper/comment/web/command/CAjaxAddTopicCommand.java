package com.xcooper.comment.web.command;

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
public class CAjaxAddTopicCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        TopicBean topicBean = new TopicBean();

        TopicVO topicVO = new TopicVO();

        topicVO.setTOPIC_ID(SeqNumHelper.getNewSeqNum("topic"));

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"),0);

        //话题名称 title
        topicVO.setTITLE(request.getParameter("title"));

        //话题描述 describes
        topicVO.setDESCRIBES(request.getParameter("describes"));

        //所属项目id projectId
        topicVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"),0));

        //创建人id

        topicVO.setCREATE_ID(memberId);

        //添加日志
        LogUtil.operaLog(memberId, OperaType.ADD, LogType.TOPIC,topicVO.getTOPIC_ID(),topicVO.getTITLE());

        try {
            topicBean.addTopic(topicVO);
            return JsonResultUtil.instance().addCode(JsonResultUtil.OK).addMsg("添加成功").addData(topicVO).json();
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
