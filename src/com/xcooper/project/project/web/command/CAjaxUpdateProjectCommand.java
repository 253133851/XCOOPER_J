package com.xcooper.project.project.web.command;

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
import com.xcooper.project.project.vo.ProjectVO;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxUpdateProjectCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        ProjectBean projectBean = new ProjectBean();

        int projectId = StrUtil.getNotNullIntValue(request.getParameter("projectId"), 0);

        ProjectVO projectVO = projectBean.getProjectByID(projectId);

        //修改项目名 projectName
        projectVO.setPROJECT_NAME(request.getParameter("projectName"));

        //修改项目描述 projectInfo
        projectVO.setPROJECT_INFO(request.getParameter("projectInfo"));

        //修改类型 type
        projectVO.setTYPE(StrUtil.getNotNullIntValue(request.getParameter("type"),0));

        //修改是否隐藏敏感 isHide
        projectVO.setIS_HIDE(StrUtil.getNotNullIntValue(request.getParameter("isHide"),-1));

        //修改是否只读 isReadOnly
        projectVO.setIS_READ_ONLY(StrUtil.getNotNullIntValue(request.getParameter("isReadOnly"),-1));

        //执行修改
        projectBean.modifyProject(projectVO);

        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
