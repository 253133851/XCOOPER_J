package com.xcooper.sys.log.web.command;

import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.log.busi.LogBean;
import com.xcooper.sys.log.vo.LogVO;

/**
 * 日志记录工具类
 * Created by ZDK on 2016/4/26.
 */
public class LogUtil {

    /**
     * 添加操作日志
     * @param operaId 操作人id
     * @param operaType 操作类型
     * @param logType 日志类型
     * @param targetId 目标id
     * @param opera 操作内容
     */
    public static void operaLog(int operaId,String operaType,String logType,int targetId,String opera){
        LogBean logBean = new LogBean();
        LogVO logVO = new LogVO();

        logVO.setOPERA_ID(operaId);
        logVO.setOPERA_TYPE(operaType);
        logVO.setLOG_TYPE(logType);
        logVO.setTARGET_ID(targetId);
        logVO.setOPERA(opera);

        try {
            logBean.addLog(logVO);
        }catch (DataAccessException e){
            e.printStackTrace();
        }

    }

    public static void operaLog(int operaId,String operaType,String logType,int targetId,String opera,String remark1){
        LogBean logBean = new LogBean();
        LogVO logVO = new LogVO();

        logVO.setOPERA_ID(operaId);
        logVO.setOPERA_TYPE(operaType);
        logVO.setLOG_TYPE(logType);
        logVO.setTARGET_ID(targetId);
        logVO.setOPERA(opera);
        logVO.setREMARK1(remark1);

        try {
            logBean.addLog(logVO);
        }catch (DataAccessException e){
            e.printStackTrace();
        }

    }

}
