package com.pabula.fw.action;

import com.pabula.fw.utility.AbstractRequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunsai on 2015/8/31.
 */
public class AjaxUploadFileHelper extends AbstractRequestHelper {

    public AjaxUploadFileHelper(HttpServletRequest request, HttpServletResponse response, ServletContext application){
        super(request, response, application);
    }

    public VO getVOClass(HttpServletRequest request) {
        return null;
    }



    public ServletContext getApplication() {
        return null;
    }

}
