package com.jiaorder.sys.member.busi;

import com.jiaorder.sys.member.dao.MemberDAO;
import com.jiaorder.sys.member.vo.MemberVO;
import com.pabula.fw.exception.DataAccessException;

import java.util.List;


/**
 * 会员Bean
 *
 * @author pabula
 *         2016-03-11 00:52:50
 */
public class MemberBean {

    private MemberDAO dao = null;

    private MemberBean() {
        dao = new MemberDAO();
    }

    private static MemberBean bean = null;

    public static MemberBean newInstance() {
        if (bean == null) {
            synchronized (MemberBean.class) {
                if (bean == null) {
                    bean = new MemberBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加会员
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean addMember(MemberVO VO) {
        return dao.addMember(VO);
    }

    /**
     * 修改会员
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean modifyMember(int serviceId, MemberVO VO) {
        return dao.modifyMember(serviceId,VO);
    }

    /**
     * 批量删除会员
     *
     * @param ids 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean delMember(int serviceId, String ids) {
        return dao.delMember(serviceId,ids);
    }

    /**
     * 根据SQL获取会员集合
     *
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public List<MemberVO> getMemberColl(int serviceId,String order,MemberVO filterMember) {
        return dao.queryMember(serviceId,order,filterMember);
    }

    /**
     * 根据SQL获取会员集合
     *
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public int getMemberColl_count(int serviceId,MemberVO filterMember) {
        return dao.queryMember_count(serviceId,filterMember);
    }

    /**
     * 根据ID取其VO
     *
     * @param MEMBER_ID
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:52:50
     */
    public MemberVO getMemberByID(int serviceId, int MEMBER_ID) {
        return dao.getMemberByID(serviceId,MEMBER_ID);
    }


}