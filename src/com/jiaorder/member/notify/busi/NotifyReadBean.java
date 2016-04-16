package com.jiaorder.member.notify.busi;

import com.jiaorder.member.notify.dao.NotifyReadDAO;
import com.jiaorder.member.notify.vo.NotifyReadVO;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * 通知已读表Bean
 * @author zdk
 * 2016-03-21 15:31:16
 */
public class NotifyReadBean {

	Logger log = Logger.getLogger(NotifyReadBean.class);
	NotifyReadDAO dao;

	public NotifyReadBean(){
		dao = new NotifyReadDAO();
	}

	private static NotifyReadBean bean = null;

	public static NotifyReadBean newInstance() {
		if (bean == null) {
			synchronized (NotifyReadBean.class) {
				if (bean == null) {
					bean = new NotifyReadBean();
				}
			}
		}
		return bean;
	}

	/**
	 * 添加通知已读表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public void addNotifyRead(NotifyReadVO VO)throws DataAccessException{
		//唯一用户分类id
		if (VO.getNOTIFY_READ_ID() < 1) {
			try {
				VO.setNOTIFY_READ_ID(SeqNumHelper.getNewSeqNum("NOTIFY_READ"));
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		if(dao.addNotifyRead(VO)){
			List<NotifyVO> notifyVOs = NotifyBean.newInstance().getNotifyByID(VO.getSERVICE_ID(), VO.getNOTIFY_ID());
			NotifyVO vo = notifyVOs.get(0);
			vo.setREAD_NUM(vo.getREAD_NUM()+1);
			NotifyBean.newInstance().modifyNotify(vo);
		}
	}

	/**
	 * 修改通知已读表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public void modifyNotifyRead(NotifyReadVO VO)throws DataAccessException{
		dao.modifyNotifyRead(VO);
	}

	/**
	 * 删除通知已读表
	 * @param NOTIFY_READ_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public void delNotifyRead(int NOTIFY_READ_ID)throws DataAccessException{
		dao.delNotifyRead(NOTIFY_READ_ID);
	}

	/**
	 * 批量删除通知已读表
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public void delNotifyRead(Collection coll)throws DataAccessException{
		dao.delNotifyRead(coll);
	}

	/**
	 * 根据SQL获取通知已读表集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public Collection getNotifyReadColl(String sql)throws DataAccessException{
		return dao.getNotifyReadColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param NOTIFY_READ_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public NotifyReadVO getNotifyReadByID(int NOTIFY_READ_ID)throws DataAccessException{
		return dao.getNotifyReadByID(NOTIFY_READ_ID);
	}
	/**
	 * 根据ID取其VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:31:16
	 */
	public List<NotifyReadVO> getNotifyReadByNid(int serviceId, int NOTIFY_ID)throws DataAccessException{
		return dao.getNotifyReadByNid(serviceId,NOTIFY_ID);
	}
}