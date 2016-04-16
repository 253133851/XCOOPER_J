package com.jiaorder.shop.tag.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.tag.busi.TagBean;
import com.jiaorder.shop.tag.vo.TagVO;
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
 * 得到所有标签command
 */
public class CAjaxGetTagCollCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        int serviceId =  UserHelper.getServiceID(request);

        //加入page格式
        TagBean bean = TagBean.newInstance();
        List<TagVO> list = bean.getTagColl(serviceId);
        Page<TagVO> page = new Page<>();
        page.setItems(list);
        String json = JsonResultUtil.instance().addData(page).json();

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"获取标签","",request);
        return json;
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
