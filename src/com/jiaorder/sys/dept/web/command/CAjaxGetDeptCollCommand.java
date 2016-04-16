package com.jiaorder.sys.dept.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.sys.dept.busi.DeptBean;
import com.jiaorder.sys.dept.vo.DeptVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 得到部门command
 */
public class CAjaxGetDeptCollCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {

    int serviceId =  UserHelper.getServiceID(request);
    DeptBean bean = DeptBean.newInstance();

    //加入page格式
    List<DeptVO> list = bean.getDeptColl(serviceId);
    Page<DeptVO> page = new Page<>();
    page.setItems(list);
    String json = JsonResultUtil.instance().addData(page).json();
    //日志记录
    LogUtil.operLog(LogType.PRODUCT,"得到部门","",request);
    return json;
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }
}
