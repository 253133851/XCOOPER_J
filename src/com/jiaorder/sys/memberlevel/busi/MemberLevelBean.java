package com.jiaorder.sys.memberlevel.busi;

import com.jiaorder.sys.memberlevel.dao.MemberLevelDAO;
import com.jiaorder.sys.memberlevel.vo.MemberLevelVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.util.List;

/**
 * 客户级别bean
 */
public class MemberLevelBean {

    private MemberLevelDAO dao = null;

    private MemberLevelBean() {
        dao = new MemberLevelDAO();
    }

    private static MemberLevelBean bean = null;

    public static MemberLevelBean newInstance() {
        if (bean == null) {
            synchronized (MemberLevelBean.class) {
                if (bean == null) {
                    bean = new MemberLevelBean();
                }
            }
        }
        return bean;
    }


    /**
     * 增加默认的客户级别
     *
     * @return
     */
    public boolean addDefaultMemberLevel() {
        try {
            MemberLevelVO memberLevelVO = new MemberLevelVO();
            if (memberLevelVO.getMEMBER_LEVEL_ID() < 1) {
                memberLevelVO.setMEMBER_LEVEL_ID(SeqNumHelper.getNewSeqNum("MEMBER_LEVEL"));
            }
            memberLevelVO.setLEVEL_NAME("普通");
            memberLevelVO.setPRICE_OFF("100");
            memberLevelVO.setIS_DEFAULT(1);
            memberLevelVO.setMEMBER_COUNT(0);
            memberLevelVO.setSERVICE_ID(1);
            memberLevelVO.setORDER_NUM(1);
            return dao.addMemberLevel(1, memberLevelVO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加客户级别
     *
     * @param vo
     * @return 执行的结果，执行成功 true，失败 false
     */
    public boolean addMemberLevel(int serviceId, MemberLevelVO vo) {
        try {
            //唯一的客户级别id
            if (vo.getMEMBER_LEVEL_ID() < 1) {
                vo.setMEMBER_LEVEL_ID(SeqNumHelper.getNewSeqNum("MEMBER_LEVEL"));
                vo.setORDER_NUM(vo.getMEMBER_LEVEL_ID());//默认客户级别排序值按ID
            }
            return dao.addMemberLevel(serviceId, vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public boolean modifyMemberLevel(int servicId, int MemberLevelid, String MemberLevels, String PayOff) {
        MemberLevelVO MemberLevel = dao.queryMemberLevelById(servicId, MemberLevelid);
        MemberLevel.setLEVEL_NAME(MemberLevels);
        MemberLevel.setPRICE_OFF(PayOff);
        return dao.updateMemberLevel(servicId, MemberLevel);
    }

    /**
     * 交换排序值
     *
     * @param servicId
     */
    public boolean exchangeMemberLevelOrderNum(int servicId, int id1, int id2) {

        if (id1 != 0 && id2 != 0) {
            MemberLevelVO memberLevelVO1 = dao.queryMemberLevelById(servicId, id1);
            MemberLevelVO memberLevelVO2 = dao.queryMemberLevelById(servicId, id2);
            int num1 = memberLevelVO1.getORDER_NUM();
            int num2 = memberLevelVO2.getORDER_NUM();
            memberLevelVO1.setORDER_NUM(num2);
            memberLevelVO2.setORDER_NUM(num1);
            dao.updateMemberLevel(servicId, memberLevelVO1);
            dao.updateMemberLevel(servicId, memberLevelVO2);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 改变客户数量
     * @param service_id
     * @param levelid
     * @param type add 为+1 del 为-1
     */
    public void updateMemberCount(int service_id,int levelid,String type){
        MemberLevelVO memberLevelVO=dao.queryMemberLevelById(service_id,levelid);
        if(type=="add") {
            memberLevelVO.setMEMBER_COUNT(memberLevelVO.getMEMBER_COUNT()+1);
        }else if(type=="del") {
            if(memberLevelVO.getMEMBER_COUNT()!=0)
            memberLevelVO.setMEMBER_COUNT(memberLevelVO.getMEMBER_COUNT()-1);
        }
        dao.updateMemberLevel(service_id,memberLevelVO);
    }

    /**
     * 返回一个serviceID下的所有客户级别
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public List<MemberLevelVO> getMemberLevelColl(int servicId) {

        List<MemberLevelVO> list = dao.queryMemberLevel(servicId);

        return list;
    }


    /**
     * 删除客户级别
     *
     * @param serviceId
     * @return
     */
    public boolean delMemberLevel(int serviceId, int Id) {
        return dao.delMemberLevel(serviceId, Id + "");

    }

}
