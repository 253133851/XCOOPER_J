package com.xcooper.tomato.web.command;

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
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.tomato.busi.TomatoBean;
import com.xcooper.tomato.vo.TomatoVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddTomatoCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        TomatoBean tomatoBean = new TomatoBean();

        TomatoVO tomatoVO = new TomatoVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        tomatoVO.setTOMATO_ID(SeqNumHelper.getNewSeqNum("tomato"));

        //添加创建人id createId
        tomatoVO.setCREATE_ID(StrUtil.getNotNullIntValue(request.getParameter("createId"),0));

        //添加对应任务id taskId
        tomatoVO.setTASK_ID(StrUtil.getNotNullIntValue(request.getParameter("taskId"),0));

        //添加开始时间 beginDatetime
        tomatoVO.setBEGIN_DATETIME(Timestamp.valueOf(request.getParameter("beginDatetime")));

        //        list.setADD_DATETIME(Timestamp.valueOf(request.getParameter("截止时间")));


        try {
            tomatoBean.addTomato(tomatoVO);
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

    //项目id
    int projectId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        projectId = StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0);
        if (projectId == 0) {
            validate.addError("项目id错误");
        }
    }
}
