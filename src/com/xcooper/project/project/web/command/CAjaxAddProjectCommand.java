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
import com.xcooper.project.projectMember.busi.ProjectMemberBean;
import com.xcooper.project.projectMember.vo.ProjectMemberVO;
import com.xcooper.task.task.busi.TaskBean;
import com.xcooper.task.task.vo.TaskVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by zdk on 2016.4.19.
 */
public class CAjaxAddProjectCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        ProjectBean projectBean = new ProjectBean();

        ProjectMemberBean projectMemberBean = new ProjectMemberBean();

        ProjectVO projectVO = new ProjectVO();

        //SeqNumHelper.getNewSeqNum("xxxx") 像VO对象中插入可用ID
        projectVO.setPROJECT_ID(SeqNumHelper.getNewSeqNum("project"));

        //添加项目名 projectName
        projectVO.setPROJECT_NAME(request.getParameter("projectName"));

        //添加项目描述 projectInfo
        projectVO.setPROJECT_INFO(request.getParameter("projectInfo"));

        //添加类型 type
        projectVO.setTYPE(StrUtil.getNotNullIntValue(request.getParameter("type"), 0));

        //添加是否隐藏敏感 isHide
        projectVO.setIS_HIDE(StrUtil.getNotNullIntValue(request.getParameter("isHide"), -1));

        //设置是否只读 isReadOnly
        projectVO.setIS_READ_ONLY(StrUtil.getNotNullIntValue(request.getParameter("isReadOnly"), -1));

        //循环插入 memberId 到 Project_Member表中
        String memberIds = request.getParameter("memberIds");

        String[] memberIdArray = memberIds.split(",");

        for (int i = 0; i < memberIdArray.length; i++) {

            ProjectMemberVO projectMemberVO = new ProjectMemberVO();

            //设置 id
            projectMemberVO.setID(SeqNumHelper.getNewSeqNum("project_member"));

            //设置当前的projectId
            projectMemberVO.setPEOJECT_ID(projectVO.getPROJECT_ID());

            //设置添加的用户id memberId
            projectMemberVO.setMEMBER_ID(StrUtil.getNotNullIntValue(memberIdArray[i], 0));

            projectMemberBean.addProjectMember(projectMemberVO);
        }

        try {
            projectBean.addProject(projectVO);
            return JsonResultUtil.instance().addData(projectVO).addExtraData(new Object[]{"所有该项目的成员信息"}).json();
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
