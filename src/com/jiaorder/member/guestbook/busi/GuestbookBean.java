package com.jiaorder.member.guestbook.busi;

import com.jiaorder.member.guestbook.dao.GuestbookDAO;
import com.jiaorder.member.guestbook.vo.GuestbookAnswerVO;
import com.jiaorder.member.guestbook.vo.GuestbookVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户反馈Bean
 *
 * @author zdk
 *         2016-03-21 15:15:28
 */
public class GuestbookBean {

    Logger log = Logger.getLogger(GuestbookBean.class);
    GuestbookDAO dao;

    public GuestbookBean() {
        dao = new GuestbookDAO();
    }

    private static GuestbookBean bean = null;

    public static GuestbookBean newInstance() {
        if (bean == null) {
            synchronized (GuestbookBean.class) {
                if (bean == null) {
                    bean = new GuestbookBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加客户反馈
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public void addGuestbook(GuestbookVO VO) throws DataAccessException {
        if (VO.getGUESTBOOK_ID() < 1) {
            try {
                VO.setGUESTBOOK_ID(SeqNumHelper.getNewSeqNum("GUESTBOOK"));
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        dao.addGuestbook(VO);
    }

    /**
     * 修改客户反馈
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public void modifyGuestbook(GuestbookVO VO) throws DataAccessException {
        dao.modifyGuestbook(VO);
    }

    /**
     * 删除客户反馈
     *
     * @param GUESTBOOK_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public void delGuestbook(int GUESTBOOK_ID) throws DataAccessException {
        dao.delGuestbook(GUESTBOOK_ID);
    }

    /**
     * 批量删除客户反馈
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public void delGuestbook(Collection coll) throws DataAccessException {
        dao.delGuestbook(coll);
    }

    /**
     * 根据SQL获取客户反馈集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public Collection getGuestbookColl(String sql) throws DataAccessException {
        return dao.getGuestbookColl(sql);
    }

    /**
     * 根据SQL获取客户反馈集合
     *
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public List<GuestbookVO> getGuestbook(int serviceId, GuestbookVO filter) throws DataAccessException {
        List<GuestbookVO> guestbookVOs = dao.getGuestbook(serviceId, filter);
        String ids = "";
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < guestbookVOs.size(); i++) {
            ids = ids + "," + guestbookVOs.get(i).getGUESTBOOK_ID();
            map.put(guestbookVOs.get(i).getGUESTBOOK_ID(), "");
        }
        if (guestbookVOs.size() > 0) {
            List<GuestbookAnswerVO> guestbookAnswerVOs = GuestbookAnswerBean.newInstance().getGuestbookAnswerByID(ids.substring(1));
            for (int j = 0; j < guestbookAnswerVOs.size(); j++) {
                String answer = map.get(guestbookAnswerVOs.get(j).getGUESTBOOK_ID());
                map.put(guestbookAnswerVOs.get(j).getGUESTBOOK_ID(), answer + "," + guestbookAnswerVOs.get(j).getCONTENT());
            }
            for (int i = 0; i < guestbookVOs.size(); i++) {
                if (map.get(guestbookVOs.get(i).getGUESTBOOK_ID()).length() > 0) {
                    guestbookVOs.get(i).setANSWER_LIST(map.get(guestbookVOs.get(i).getGUESTBOOK_ID()).substring(1));
                }
            }
        }
        return guestbookVOs;
    }

    /**
     * 获取相同条件下的结果数量
     */
    public int getGuestbookcount(int serviceId, GuestbookVO filter) throws DataAccessException {
        return dao.getGuestbookCount(serviceId, filter);
    }

    /**
     * 根据ID取其VO
     *
     * @param GUESTBOOK_ID
     * @throws DataAccessException
     * @author zdk 2016-03-21 15:15:28
     */
    public GuestbookVO getGuestbookByID(int serviceId, int GUESTBOOK_ID) throws DataAccessException {
        return dao.getGuestbookByID(serviceId, GUESTBOOK_ID);
    }

    public static void main(String[] args) {
        GuestbookVO vo = new GuestbookVO();
        vo.setSERVICE_ID(1015);
        vo.setMEMBER_ID(2);
        vo.setNOTIFY_BACK("我有一头小毛驴");
        try {
            GuestbookBean.newInstance().addGuestbook(vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


}