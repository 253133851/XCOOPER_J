package com.xcooper.sys.file.web.command;

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
import com.xcooper.list.busi.ListBean;
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
public class CAjaxDeleteFileCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);

        int memberId = StrUtil.getNotNullIntValue(request.getParameter("memberId"), 0);

        FileBean fileBean = new FileBean();

        FileVO fileVO = fileBean.getFileByID(id);

        //添加日志
        LogUtil.operaLog(memberId, OperaType.Delete, LogType.FILE,id,fileVO.getFILE_NAME());

        try {
            //删除该id的记录
            fileBean.delFile(id);
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
