package com.jiaorder.sys.log;

import com.jiaorder.sys.log.busi.OperLogBean;
import com.jiaorder.sys.log.busi.OrderLogBean;
import com.jiaorder.sys.log.vo.OperLogVO;
import com.jiaorder.sys.log.vo.OrderLogVO;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.IPUtil;
import com.pabula.fw.exception.DataAccessException;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志记录工具类
 * Created by Pabula on 16/3/17 13:34.
 */
public class LogUtil {



    /**
     * 添加系统操作日志
     * @param type  类型
     * @param logInfo   日志
     * @param serviceID
     * @param UID
     * @param targetDataID  操作的数据ID
     * @param companyName 公司的名称,不是ID
     * @param userName 用户的名称,不是ID
     * @param IP
     */
    public static void operLog(String type,String logInfo,int serviceID,int UID,String targetDataID,String companyName,String userName,String IP){

        OperLogBean logBean = new OperLogBean();

        OperLogVO logVO = new OperLogVO();

        logVO.setSERVICE_ID(serviceID);	//int  SERVICE_ID
        logVO.setUID(UID);	//int  UID
        logVO.setTARGET_ID(targetDataID);	//String  操作目标ID
        logVO.setOPER_COMPANY(companyName);	//String  操作公司
        logVO.setOPER_USER(userName);	//String  操作人
        logVO.setOPER_DATETIME(DateUtil.getNowTime());	//Timestamp  OPER_DATETIME
        logVO.setTYPE(type);	//String  TYPE
        logVO.setOPER_LOG(logInfo);	//String  OPER_LOG
        logVO.setOPER_IP(IP);	//String  OPER_IP

        try {
            logBean.addOperLog(logVO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }


    /**
     * 添加系统操作日志
     * @param type  日志类型
     * @param logInfo   日志
     * @param targetDataID  操作的数据目标ID
     * @param request
     */
    public static void operLog(String type,String logInfo,int targetDataID, HttpServletRequest request){
        operLog(type,logInfo,String.valueOf(targetDataID),request);
    }


    /**
     * 添加系统操作日志
     * @param type  日志类型
     * @param logInfo   日志
     * @param targetDataID  操作的数据目标ID
     * @param request
     */
    public static void operLog(String type,String logInfo,String targetDataID, HttpServletRequest request){
        UserVO userVO = UserHelper.getUserInfo(request);

        operLog(type,logInfo,userVO.getSERVICE_ID(),userVO.getUID(),targetDataID,"",userVO.getUSER_NAME(), IPUtil.getRemortIP(request));
    }



    /**
     * 添加订单日志
     * @param type  类型
     * @param logInfo
     * @param serviceID
     * @param UID
     * @param orderID   订单ID
     * @param companyName 公司的汉字名称,不是ID
     * @param userName  用户的名称,不是ID
     * @param IP
     */
    public static void orderLog(String type,String logInfo,int serviceID,int UID,String orderID,String companyName,String userName,String IP){

        OrderLogBean logBean = new OrderLogBean();

        OrderLogVO logVO = new OrderLogVO();

        logVO.setSERVICE_ID(serviceID);	//int  SERVICE_ID
        logVO.setUID(UID);	//int  UID
        logVO.setORDER_ID(orderID);	//String  操作目标ID
        logVO.setOPER_COMPANY(companyName);	//String  操作公司
        logVO.setOPER_USER(userName);	//String  操作人
        logVO.setOPER_DATETIME(DateUtil.getNowTime());	//Timestamp  OPER_DATETIME
        logVO.setTYPE(type);	//String  TYPE
        logVO.setOPER_LOG(logInfo);	//String  OPER_LOG
        logVO.setOPER_IP(IP);	//String  OPER_IP

        try {
            logBean.addOrderLog(logVO);

            //同时添加一个操作日志记录
            operLog(type,logInfo,serviceID,UID,orderID,companyName,userName,IP);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


    }

    /**
     * 添加订单日志
     * @param type  日志类型
     * @param logInfo   日志
     * @param orderID   订单ID
     * @param request   REQUEST对象
     */
    public static void orderLog(String type, String logInfo, String orderID, HttpServletRequest request) {

        UserVO userVO = UserHelper.getUserInfo(request);

        orderLog( type,  logInfo,  userVO.getSERVICE_ID(),  userVO.getUID(),  orderID,  "",
                userVO.getUSER_NAME(), IPUtil.getRemortIP(request));
    }


}
