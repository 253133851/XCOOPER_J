package com.jiaorder.sys.file.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.file.busi.FileBean;
import com.jiaorder.sys.file.vo.FileVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunsai on 2016/3/23 - 16:43.
 */
public class CAjaxGetFileCollCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {

    int serviceId = UserHelper.getServiceID(request);

    int targetId = StrUtil.getNotNullIntValue(request.getParameter("targetId"));
    String type = request.getParameter("type");

    SqlHelper sh = new SqlHelper();

    sh.setTable("FILE");
    sh.setSelectColumn("*");
    sh.setWhereForInt("SERVICE_ID", "=", serviceId);
    sh.setWhereForInt("TARGET_ID", "=", targetId);
    sh.setWhereForString("TYPE", "=", type);

    List<FileVO> result = FileBean.newInstance().getFileColl(sh.getSQL(sh.getSelectSQL()));

    return new Page<>(request, result, sh, "count(*) COUNT").json();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

  }
}
