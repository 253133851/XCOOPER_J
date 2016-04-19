package com.xcooper.project.project.web.command;

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
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxAddProjectCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        ProjectBean projectBean = new ProjectBean();

        ProjectVO projectVO = new ProjectVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        projectVO.setPROJECT_ID(SeqNumHelper.getNewSeqNum("project"));

        //添加项目名 projectName
        projectVO.setPROJECT_NAME(request.getParameter("projectName"));

        //添加项目描述 projectInfo
        projectVO.setPROJECT_INFO(request.getParameter("projectInfo"));

        //添加类型 type
        projectVO.setTYPE(StrUtil.getNotNullIntValue(request.getParameter("type"),0));

        //添加是否隐藏敏感 isHide
        projectVO.setIS_HIDE(StrUtil.getNotNullIntValue(request.getParameter("isHide"),-1));

        //设置是否只读 isReadOnly
        projectVO.setIS_READ_ONLY(StrUtil.getNotNullIntValue(request.getParameter("isReadOnly"),-1));


        try {
            projectBean.addProject(projectVO);
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
