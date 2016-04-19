package com.xcooper;

/**
 * Created by sunsai on 2016/2/19.
 */
public class ENV {

    //是否为测试模式
    public static boolean IS_TEST = true;//

    // 公司IP
    final public static String SQL = "192.168.2.200";
    //  家里的IP
//  final public static String SQL = "192.168.99.182";


    //ssss
    //业务的主包名称，所有的模块都在这个大包下
    final public static String PACKAGE_NAME = "com.xcooper";
    final public static String VO_PACKAGE_NAME = "com.xcooper";

    //cookie中存放的用户校验码加密KEYVO_
    public static final String MEMBER_COOKIE_PWD_CHECK = "hhdaojiapabulaveryok";

    //cookie中存放的购物车的cookie名称
    public static final String CART_COOKIE_NAME = "PRDCART";

    //短信相关参数（http://www.yuntongxun.com)
    public static final String SMS_TELPLET_ID = "72581";    //验证码模板ID
    public static final String SMS_TELPLET_ID_NEWPWD = "72824";    //验证码模板ID
    public static final String SMS_TELPLET_ID_RESETPWD = "72680";    //验证码模板ID
    public static final String SMS_APP_ID = "8a48b55150a898370150a9eb2b710512"; //应用ID
    public static final String SMS_ACCOUNT_SID = "aaf98f894e2360b4014e32ba8eba0d30";    //ACCOUNT_SID
    public static final String SMS_AUTH_TOKEN = "40b12dfec56c4f1fa66eaa3ff0903a7c"; //AUTH_TOKEN
    public static final String SMS_SERVER = "app.cloopen.com"; //AUTH_TOKEN
    public static final String SMS_SERVER_PORT = "8883"; //AUTH_TOKEN

    //URL domain
    public static String URL_DOMAIN = "http://app.huanhuan.la";


    public static final String JSON_FILTER_NAME = "jiaOrderFilterName";

}
