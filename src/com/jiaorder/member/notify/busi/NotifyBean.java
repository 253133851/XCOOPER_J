package com.jiaorder.member.notify.busi;

import com.jiaorder.member.notify.dao.NotifyDAO;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通知公告Bean
 *
 * @author cwq
 *         2016-03-21 15:02:59
 */
public class NotifyBean {

    Logger log = Logger.getLogger(NotifyBean.class);
    NotifyDAO dao;

    public NotifyBean() {
        dao = new NotifyDAO();
    }

    private static NotifyBean bean = null;

    public static NotifyBean newInstance() {
        if (bean == null) {
            synchronized (NotifyBean.class) {
                if (bean == null) {
                    bean = new NotifyBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加通知公告
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public Boolean addNotify(NotifyVO VO) {
        VO.setADD_DATETIME(DateUtil.getCurrTime());
        return dao.addNotify(VO);
    }

    /**
     * 修改通知公告
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void modifyNotify(NotifyVO VO) throws DataAccessException {
        dao.modifyNotify(VO);
    }


    /**
     * 修改通知公告ID
     *
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public boolean modifyNotifyId(int serviceId, int oldId, int newId) {
        return dao.modifyNotifyId(serviceId, oldId, newId);
    }

    /**
     * 删除通知公告
     *
     * @param NOTIFY_ID
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void delNotify(int NOTIFY_ID) throws DataAccessException {
        dao.delNotify(NOTIFY_ID);
    }

    /**
     * 批量删除通知公告
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public void delNotify(Collection coll) throws DataAccessException {
        dao.delNotify(coll);
    }

    /**
     * 根据SQL获取通知公告集合
     *
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public List<NotifyVO> getNotifyColl(int serviceId, NotifyVO filterNotifyVO, int uid) throws DataAccessException {
        List<NotifyVO> list = dao.queryNotify(serviceId, filterNotifyVO);
        return filter_aim_notify(serviceId, uid, list, filterNotifyVO,true);
    }

    /**
     * 过滤aim_peoplele类型的notify
     *
     * @param serviceId
     * @param uid
     * @param list
     * @return
     * @throws DataAccessException
     */
    public List<NotifyVO> filter_aim_notify(int serviceId, int uid, List<NotifyVO> list, NotifyVO filter,boolean withLimit) throws DataAccessException {
        List<NotifyVO> filter_list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            NotifyVO notify = list.get(i);
            if (notify.getNOTIFY_SHOW().equals("AIM_PEOPLE")) {
                if (NotifyUserBean.newInstance().checkReadableByUid(serviceId, uid, notify.getNOTIFY_ID())) {
                    filter_list.add(notify);
                }
            } else {
                filter_list.add(notify);
            }
        }
        if(withLimit) {
            List<NotifyVO> result = new ArrayList<>();
            int start = (filter.getPageIndex() - 1) * filter.getPageSize();
            for (int i = start; i < start + filter.getPageSize(); i++) {
                if(i<filter_list.size())
                result.add(filter_list.get(i));
            }
            return result;
        }else{
            return filter_list;
        }

    }

    /**
     * 获取通知公告集合条数
     *
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public int getNotifyColl_count(int serviceId, NotifyVO filterNotifyVO, int uid) throws DataAccessException {
        List<NotifyVO> list = dao.queryNotify_count(serviceId, filterNotifyVO);
        return filter_aim_notify(serviceId, uid, list, filterNotifyVO,false).size();
    }

    /**
     * 根据ID取其VO
     *
     * @param NOTIFY_ID
     * @throws DataAccessException
     * @author cwq 2016-03-21 15:02:59
     */
    public List<NotifyVO> getNotifyByID(int serviceId, int NOTIFY_ID) throws DataAccessException {
        List<NotifyVO> list = new ArrayList<NotifyVO>();
        list.add(dao.getNotifyByID(serviceId, NOTIFY_ID));
        return list;
    }


    public static void main(String[] args) {
        NotifyBean bean = NotifyBean.newInstance();
        NotifyVO vo = new NotifyVO();
        vo.setTITLE("我有三头小毛驴");
        vo.setCLASS_ID(4);
        vo.setREAD_NUM(2);
        bean.addNotify(vo);
    }
}