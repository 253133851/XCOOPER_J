package com.jiaorder.member.notify.web.command;

import com.jiaorder.member.notify.busi.NotifyBean;
import com.jiaorder.member.notify.busi.NotifyUserBean;
import com.jiaorder.member.notify.vo.NotifyUserVO;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.jiaorder.sys.file.FileTye;
import com.jiaorder.sys.file.busi.FileBean;
import com.jiaorder.sys.file.vo.FileVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 新增通知command
 */
public class CAjaxAddNotifyCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        try {
            notifyId = SeqNumHelper.getNewSeqNum("NOTIFY");
            if (show.equals("AIM_PEOPLE")) {
                String[] targetUserIdList = targetUserIds.split(",");
                String[] targetMemberList = targetMemberIds.split(",");
                List<NotifyUserVO> list = new ArrayList<>();
                for (int i = 0; i < targetUserIdList.length; i++) {
                    NotifyUserVO notifyUserVO = new NotifyUserVO();
                    notifyUserVO.setNOTIFY_ID(notifyId);
                    notifyUserVO.setNOTIFY_USER_ID(SeqNumHelper.getNewSeqNum("NOTIFY_USER"));
                    notifyUserVO.setTYPE("USER");
                    notifyUserVO.setSERVICE_ID(serviceId);
                    notifyUserVO.setTARGET_ID(Integer.parseInt(targetUserIdList[i]));
                    list.add(notifyUserVO);
                }
                for (int i = 0; i < targetMemberList.length; i++) {
                    NotifyUserVO notifyUserVO = new NotifyUserVO();
                    notifyUserVO.setNOTIFY_ID(notifyId);
                    notifyUserVO.setNOTIFY_USER_ID(SeqNumHelper.getNewSeqNum("NOTIFY_USER"));
                    notifyUserVO.setTYPE("MEMBER");
                    notifyUserVO.setSERVICE_ID(serviceId);
                    notifyUserVO.setTARGET_ID(Integer.parseInt(targetMemberList[i]));
                    list.add(notifyUserVO);
                }
                NotifyUserBean.newInstance().addNotifyUserList(list);
            }

            int length = StrUtil.getNotNullIntValue(request.getParameter("fileUrlLength"));
            System.out.println(length);
            for (int i = 0; i < length; i++) {
                String url = request.getParameter("fileUrls[" + i + "][url]");
                String fileName = request.getParameter("fileUrls[" + i + "][original]");
                int size = StrUtil.getNotNullIntValue(request.getParameter("fileUrls[" + i + "][size]"));
                String fileType = request.getParameter("fileUrls[" + i + "][type]");
                FileVO fileVO = new FileVO();
                fileVO.setFILE_ID(SeqNumHelper.getNewSeqNum("FILE"));
                fileVO.setTARGET_ID(notifyId);
                fileVO.setSERVICE_ID(serviceId);
                fileVO.setFILE_NAME(fileName);
                fileVO.setFILE_TYPE(fileType);
                fileVO.setTYPE(FileTye.NOTIFY);
                fileVO.setSIZE(size);
                fileVO.setFILE_PATH(url);
                FileBean.newInstance().addFile(fileVO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        //日志记录
        LogUtil.operLog(LogType.OTHER, "新增通知", title, request);

        //创建单位vo对象并且新增到数据库
        NotifyVO vo = new NotifyVO();
        vo.setTITLE(title);
        vo.setINTRO(intro);
        vo.setAD_IMG(imageUrls);
        vo.setTYPE(type);
        vo.setAD_IMG(imageUrls);
        vo.setNOTIFY_ID(notifyId);
        vo.setCLASS_ID(classId);
        vo.setNOTIFY_SHOW(show);
        vo.setSERVICE_ID(serviceId);
        if (NotifyBean.newInstance().addNotify(vo)) {
            return JsonResultUtil.ok();
        }

        return JsonResultUtil.error();
    }

    int serviceId, classId, notifyId = 0;
    String intro, fileUrls, imageUrls, show, type, title, targetUserIds, targetMemberIds;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {
        //接收单位相关数据
        serviceId = UserHelper.getServiceID(request);
        intro = request.getParameter("intro");
        fileUrls = request.getParameter("fileUrls");
        imageUrls = request.getParameter("imageUrls");
        show = request.getParameter("show");
        targetUserIds = request.getParameter("targetUserIds");
        targetMemberIds = request.getParameter("targetMemberIds");
        classId = StrUtil.getNotNullIntValue(request.getParameter("type"), 0);
        if (classId != 0 && classId == NotifyVO.TYPE_AD) {
            type = "AD";
        } else if (classId != 0) {
            type = "NOTIFY";
        }
        title = request.getParameter("title");
    }


}
