package com.xcooper.sys.file.web.command;

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
import com.xcooper.sys.file.busi.FileBean;
import com.xcooper.sys.file.vo.FileVO;
import com.xcooper.sys.log.web.command.LogType;
import com.xcooper.sys.log.web.command.LogUtil;
import com.xcooper.sys.log.web.command.OperaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.5.3.
 */
public class CAjaxAddFileCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        FileBean fileBean = new FileBean();

        FileVO fileVO = new FileVO();

        // id
        fileVO.setID(SeqNumHelper.getNewSeqNum("file"));

        //所属项目 projectId
        fileVO.setPROJECT_ID(StrUtil.getNotNullIntValue(request.getParameter("projectId"),0));

        //类型 type
        fileVO.setTYPE(StrUtil.getNotNullIntValue(request.getParameter("type"),0));

        //文件名 fileName
        fileVO.setFILE_NAME(request.getParameter("fileName"));

        //文件后缀 fileType
        fileVO.setFILE_TYPE(request.getParameter("fileType"));

        //文件大小 fileSize
        fileVO.setFILE_SIZE(request.getParameter("fileSize"));

        //父分类路径 parentClassId
        fileVO.setPARENT_CLASS_ID(request.getParameter("parentClassId"));

        ///分类路径 classPath
        fileVO.setCLASS_PATH(request.getParameter("classPath"));

        //url
        fileVO.setURL(request.getParameter("url"));

        //创建人 createId
        int createId = StrUtil.getNotNullIntValue(request.getParameter("createId"),0);
        fileVO.setCREATE_ID(createId);

        //添加日志
        LogUtil.operaLog(createId, OperaType.ADD, LogType.FILE,fileVO.getID(),fileVO.getFILE_NAME());

        try {
            fileBean.addFile(fileVO);
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
