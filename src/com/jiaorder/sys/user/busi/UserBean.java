package com.jiaorder.sys.user.busi;

import com.jiaorder.sys.company.busi.CompanyBean;
import com.jiaorder.sys.company.vo.CompanyVO;
import com.jiaorder.sys.manager.busi.ManagerBean;
import com.jiaorder.sys.manager.vo.ManagerVO;
import com.jiaorder.sys.service.busi.ServiceBean;
import com.jiaorder.sys.service.vo.ServiceVO;
import com.jiaorder.sys.user.dao.UserDAO;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.MD5;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import java.util.Collection;
import java.util.List;

/**
 * 用户Bean
 *
 * @author pabula
 *         2016-03-11 00:49:22
 */
public class UserBean {

    private static UserBean bean = null;

    public static UserBean newInstance() {
        if (bean == null) {
            synchronized (UserBean.class) {
                if (bean == null) {
                    bean = new UserBean();
                }
            }
        }
        return bean;
    }

    UserDAO dao = null;

    public UserBean() {
        dao = new UserDAO();
    }

    /**
     * 添加用户
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void addUser(UserVO VO) throws DataAccessException {
        dao.addUser(VO);
    }

    /**
     * 添加用户并开通服务
     *
     * @param VO
     * @throws DataAccessException
     */
    public void addUserAndService(UserVO VO) throws DataAccessException {
        int companyID = SeqNumHelper.getNewSeqNum("COMPANY");
        int serviceID = SeqNumHelper.getNewSeqNum("SERIVCE");
        int managerID = SeqNumHelper.getNewSeqNum("COMPANY_MANAGER");

        //添加服务SERVICE
        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setADD_UID(VO.getUID());
        serviceVO.setCOMPANY_ID(companyID);
        serviceVO.setIS_DEF(1);
        serviceVO.setSERVICE_ID(serviceID);
        serviceVO.setSTATE(1);
        serviceVO.setVER("free");

        //添加公司COMPANY
        CompanyVO companyVO = new CompanyVO();
        companyVO.setCOMPANY_ID(companyID);
        companyVO.setSERVICE_ID(serviceID);

        //添加公司管理员
        ManagerVO managerVO = new ManagerVO();
        managerVO.setMANAGER_ID(managerID);
        managerVO.setUID(VO.getUID());
        managerVO.setROLE_ID_LIST("sa");

        new ServiceBean().addService(serviceVO);    //添加服务
        new CompanyBean().addCompany(companyVO);    //添加公司
        new ManagerBean().addManager(managerVO);    //添加公司管理员

        VO.setSERVICE_ID(serviceID);    //所属服务
        dao.addUser(VO);    //添加USER

    }

    /**
     * 修改用户
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void modifyUser(UserVO VO) throws DataAccessException {
        dao.modifyUser(VO);
    }

    /**
     * 批量修改用户开通禁用状态
     */
    public boolean updateMemberState(int serviceId,String uids,int state) {
       return  dao.updateMemberState(serviceId,uids,state);
    }

    /**
     * 删除用户
     *
     * @param UID
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void delUser(int UID) throws DataAccessException {
        dao.delUser(UID);
    }

    /**
     * 批量删除用户
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void delUser(Collection coll) throws DataAccessException {
        dao.delUser(coll);
    }

    /**
     * 根据SQL获取用户集合
     *
     * @param sql
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public Collection getUserColl(String sql) throws DataAccessException {
        return dao.getUserColl(sql);
    }

   /**
     * 根据SQL获取用户集合
     *
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public List<UserVO> getUserByFilter(int ServiceId, String filter) throws DataAccessException {
        return dao.getUserByFilter(ServiceId,filter);
    }

    /**
     * 根据ID取其VO
     *
     * @param UID
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public UserVO getUserByID(int UID) throws DataAccessException {
        return dao.getUserByID(UID);
    }

    /**
     * 根据用户手机号获得用户
     *
     * @param phone
     * @return
     * @throws DataAccessException
     */
    public UserVO getUserByPhone(String phone) throws DataAccessException {
        return dao.getUserByPhone(phone);
    }

    /**
     * 根据LOGIN_ID取得用户
     *
     * @param loginID
     * @return
     * @throws DataAccessException
     */
    public UserVO getUserForLoginID(String loginID) throws DataAccessException {
        return dao.getUserForLoginID(loginID);
    }

    /**
     * 判断用户的手机号是否有有效用户
     *
     * @param phone
     * @return
     */
    public boolean isExistUserPhone(String phone) {
        boolean is = false;

        if (StrUtil.isNotNull(phone)) {
            UserVO uservo = null;
            try {
                uservo = this.getUserByPhone(phone);
            } catch (DataAccessException e) {
                e.printStackTrace();
                return is;
            }

            if (null != uservo) {
                if (uservo.getUID() > 0) {
                    is = true;
                }
            }
        }

        return is;
    }


    /**
     * 修改用户密码
     *
     * @param mobile
     * @param pwd
     * @throws DataAccessException
     */
    public void modifyUserPWD(String mobile, String pwd) throws DataAccessException {
        String nmd5Pwd = MD5.MD5Encode(pwd);
        dao.modifyUserPWD(mobile, nmd5Pwd);
    }


    /**
     * 修改用户密码
     *
     * @param uid
     * @param pwd
     * @throws DataAccessException
     */
    public void modifyUserPWD(int uid, String pwd) throws DataAccessException {
        String nmd5Pwd = MD5.MD5Encode(pwd);
        dao.modifyUserPWD(uid, nmd5Pwd);
    }
}