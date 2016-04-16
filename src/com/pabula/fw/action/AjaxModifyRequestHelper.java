package com.pabula.fw.action;

import com.xcooper.ENV;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.AbstractRequestHelper;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �첽�ύ�޸ı?
 * Created by sunsai on 2015/9/3.
 */
public class AjaxModifyRequestHelper  extends AbstractRequestHelper {

    public AjaxModifyRequestHelper(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
        super(request, response, application);

        // ��request�е�ֵ������VO��
        VO vo = this.getVOClass(request);
        super.requestValueToVO(request, vo);

        super.setVO(vo);
    }

    /**
     * �ع�getCommand��������ݵ�validate����
     */
    public Command getCommand() throws RuleException {
        Command command = super.getCommand();

        //ִ��ʵ���ҵ��Command�е�validate ���� ������ݼ�⣬�������ݲ��Ϸ��ģ����׳�RuleException����CMSController����
        ValidateUtil validate = new ValidateUtil();
        command.validate(getRequest(),getVO(),validate);

        if (validate.hasError()) {
            //�Ѵ����list����ָ���Ĵ�����ҳ���д��?��ʾ
            RuleException e = new RuleException();
            e.setErrColl(validate.getErrors());
            throw e;
        }

        return command;
    }


    public ServletContext getApplication() {
        return null;
    }



    /**
     * ���action���󣬶�̬����ҵ���VO��
     * @param request
     * @return
     * @author pabula 2015-6-27 ����11:35:16
     */
    public VO getVOClass(HttpServletRequest request) {
        VO vo = null;

        String voClassMainName = "";

        //VO��������ƾ���actionֵȥ��ǰ���add�ַ�����action=addMember����ȥ��add������member
        String busiName = (String)(StrUtil.split(request.getParameter("action"), '!').get(0));
        String action = (String)(StrUtil.split(request.getParameter("action"),'!').get(1));
        voClassMainName = action.substring("AjaxModify".length());

        //ƴ�ӳ� ҵ��vo ����·�� ������ com.pabula.hh.Member.vo.MemberVO
        String className = ENV.VO_PACKAGE_NAME + "." + busiName + ".vo." + voClassMainName + "VO";

        System.err.println("VOClass: " + className);

        try {
            //��̬����vo��
            vo = (VO) Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return vo;
    }
}
