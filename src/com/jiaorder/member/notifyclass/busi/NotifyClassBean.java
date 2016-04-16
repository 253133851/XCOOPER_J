package com.jiaorder.member.notifyclass.busi;

import com.jiaorder.member.notifyclass.dao.NotifyClassDAO;
import com.jiaorder.member.notifyclass.vo.NotifyClassVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 通知分类表Bean
 *
 * @author zdk
 *         2016-03-21 15:33:41
 */
public class NotifyClassBean {

    Logger log = Logger.getLogger(NotifyClassBean.class);
    NotifyClassDAO dao;

    public NotifyClassBean() {
        dao = new NotifyClassDAO();
    }

    private static NotifyClassBean bean = null;

    public static NotifyClassBean newInstance() {
        if (bean == null) {
            synchronized (NotifyClassBean.class) {
                if (bean == null) {
                    bean = new NotifyClassBean();
                }
            }
        }
        return bean;
    }


    /**
     * 添加通知分类表
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean addNotifyClass(NotifyClassVO VO) {
        //唯一ID
        if (VO.getNOTIFY_CLASS_ID() < 1) {
            try {
                VO.setNOTIFY_CLASS_ID(SeqNumHelper.getNewSeqNum("notify_class"));
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        //排序值默认为ID
        VO.setORDER_NUM(VO.getNOTIFY_CLASS_ID());
        return dao.addNotifyClass(VO);
    }

    /**
     * 修改通知分类表
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean modifyNotifyClass(int serviceId,int calssID,String name){
        NotifyClassVO notifyClassVO= dao.getNotifyClassByID(serviceId,calssID);
        notifyClassVO.setNAME(name);
        return dao.modifyNotifyClass(serviceId,notifyClassVO);
    }

    /**
     * 删除通知分类表
     *
     * @param NOTIFY_CLASS_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public boolean delNotifyClass(int serviceId, int NOTIFY_CLASS_ID){
        NotifyClassVO vo = dao.getNotifyClassByID(serviceId, NOTIFY_CLASS_ID);
        vo.setIS_DEL(NotifyClassVO.HAS_DELETED);
        return dao.modifyNotifyClass(serviceId,vo);
    }


    /**
     * 根据SQL获取通知分类表集合
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public List<NotifyClassVO> getNotifyClassColl(int serviceId) throws DataAccessException {
        return dao.queryNotifyClass(serviceId);
    }

    /**
     * 根据ID取其VO
     *
     * @param NOTIFY_CLASS_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:33:41
     */
    public NotifyClassVO getNotifyClassByID(int serviceId, int NOTIFY_CLASS_ID) throws DataAccessException {
        return dao.getNotifyClassByID(serviceId,NOTIFY_CLASS_ID);
    }
}