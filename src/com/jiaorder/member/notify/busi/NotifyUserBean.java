package com.jiaorder.member.notify.busi;

import com.jiaorder.member.notify.dao.NotifyUserDAO;
import com.jiaorder.member.notify.vo.NotifyUserVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * 通知-用户表Bean
 * @author zdk
 * 2016-03-21 15:29:43
 */
public class NotifyUserBean {

	Logger log = Logger.getLogger(NotifyUserBean.class);
	NotifyUserDAO dao;

	public NotifyUserBean(){
		dao = new NotifyUserDAO();
	}

	private static NotifyUserBean bean = null;

	public static NotifyUserBean newInstance() {
		if (bean == null) {
			synchronized (NotifyUserBean.class) {
				if (bean == null) {
					bean = new NotifyUserBean();
				}
			}
		}
		return bean;
	}
	/**
	 * 添加通知-用户表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public void addNotifyUser(NotifyUserVO VO)throws DataAccessException{
		//唯一用户分类id
		if (VO.getNOTIFY_ID() < 1) {
			try {
				VO.setNOTIFY_ID(SeqNumHelper.getNewSeqNum("NOTIFY"));
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		dao.addNotifyUser(VO);
	}

	/**
	 * 批量添加通知-用户表
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public void addNotifyUserList(List<NotifyUserVO> VOs)throws DataAccessException{
		dao.addNotifyUserList(VOs);
	}



	/**
	 * 修改通知-用户表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public void modifyNotifyUser(NotifyUserVO VO)throws DataAccessException{
		dao.modifyNotifyUser(VO);
	}

	/**
	 * 删除通知-用户表
	 * @param NOTIFY_USER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public void delNotifyUser(int NOTIFY_USER_ID)throws DataAccessException{
		dao.delNotifyUser(NOTIFY_USER_ID);
	}

	/**
	 * 批量删除通知-用户表
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public void delNotifyUser(Collection coll)throws DataAccessException{
		dao.delNotifyUser(coll);
	}

	/**
	 * 根据SQL获取通知-用户表集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public Collection getNotifyUserColl(String sql)throws DataAccessException{
		return dao.getNotifyUserColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public List<NotifyUserVO> getNotifyUserByNID(int serviceId, int NOTIFY_ID)throws DataAccessException{
		return dao.getNotifyUserByNID(serviceId,NOTIFY_ID);
	}

	/**
	 * 根据UID判断通知权限
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:29:43
	 */
	public boolean checkReadableByUid(int serviceId,int userId,int notifyId)throws DataAccessException{
		return dao.checkReadableByUid(serviceId,userId,notifyId);
	}
}