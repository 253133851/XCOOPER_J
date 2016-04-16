package com.pabula.fw.utility;


import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunsai on 2015/9/1.
 */
public class JsonUtil {

    public static List<Object> getObjectListFromString(String jsonStr,Class clazz) {
        String json = "{list:[{name:'test1'},{name:'test2'}]}";
        Map classMap = new HashMap();
        classMap.put("list", clazz);
        //ʹ�ð�ʾ��ֱ�ӽ�json����Ϊָ���Զ����������List��ȫ����,Mapû����ȫ����
        List<Object> list = (List<Object>)JSONObject.toBean(JSONObject.fromObject(json),clazz , classMap);

        return  list;
    }

}
