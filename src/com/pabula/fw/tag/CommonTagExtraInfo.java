package com.pabula.fw.tag;

import com.xcooper.ENV;
import com.pabula.common.util.StrUtil;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * Created by Pabula on 2015/7/1.
 */
public class CommonTagExtraInfo  extends TagExtraInfo {
    public VariableInfo[] getVariableInfo(TagData data) {
        String voClass = data.getAttributeString("vo");
        if(StrUtil.isNull(voClass)){
            voClass = ENV.PACKAGE_NAME + "." + data.getAttributeString("name").toLowerCase() + ".vo." + data.getAttributeString("name") + "VO";
        }

        return new VariableInfo[]{
                new VariableInfo(data.getAttributeString("name"),
                        voClass,
                        true,
                        VariableInfo.AT_BEGIN)
        };
    }
}
