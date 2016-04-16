package com.jiaorder.member.guestbook.busi;

import com.jiaorder.member.guestbook.dao.GuestbookAnswerDAO;
import com.jiaorder.member.guestbook.vo.GuestbookAnswerVO;
import com.jiaorder.member.guestbook.vo.GuestbookVO;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * 客户反馈回复Bean
 * @author zdk
 * 2016-03-21 15:21:06
 */
public class GuestbookAnswerBean {

	Logger log = Logger.getLogger(GuestbookAnswerBean.class);
	GuestbookAnswerDAO dao;

	public GuestbookAnswerBean(){
		dao = new GuestbookAnswerDAO();
	}

	private static GuestbookAnswerBean bean = null;

	public static GuestbookAnswerBean newInstance() {
		if (bean == null) {
			synchronized (GuestbookAnswerBean.class) {
				if (bean == null) {
					bean = new GuestbookAnswerBean();
				}
			}
		}
		return bean;
	}

	/**
	 * 添加客户反馈回复
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public void addGuestbookAnswer(GuestbookAnswerVO VO)throws DataAccessException{
		if (VO.getGUESTBOOK_ANSWER_ID() < 1) {
			try {
				VO.setGUESTBOOK_ANSWER_ID(SeqNumHelper.getNewSeqNum("GUESTBOOK_ANSWER"));
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		VO.setADD_DATETIME(DateUtil.getCurrTime());
		dao.addGuestbookAnswer(VO);

		GuestbookVO guestbook= GuestbookBean.newInstance().getGuestbookByID(VO.getSERVICE_ID(),VO.getGUESTBOOK_ID());
		guestbook.setSTATE(GuestbookVO.HAS_ANSWER);
		GuestbookBean.newInstance().modifyGuestbook(guestbook);
	}

	/**
	 * 修改客户反馈回复
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public void modifyGuestbookAnswer(GuestbookAnswerVO VO)throws DataAccessException{
		dao.modifyGuestbookAnswer(VO);
	}

	/**
	 * 删除客户反馈回复
	 * @param GUESTBOOK_ANSWER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public void delGuestbookAnswer(int GUESTBOOK_ANSWER_ID)throws DataAccessException{
		dao.delGuestbookAnswer(GUESTBOOK_ANSWER_ID);
	}

	/**
	 * 批量删除客户反馈回复
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public void delGuestbookAnswer(Collection coll)throws DataAccessException{
		dao.delGuestbookAnswer(coll);
	}

	/**
	 * 根据SQL获取客户反馈回复集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public Collection getGuestbookAnswerColl(String sql)throws DataAccessException{
		return dao.getGuestbookAnswerColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param GUESTBOOK_ANSWER_IDS
	 * @throws DataAccessException
	 * @author zdk 2016-03-21 15:21:06
	 */
	public List<GuestbookAnswerVO> getGuestbookAnswerByID(String GUESTBOOK_ANSWER_IDS)throws DataAccessException{
		return dao.getGuestbookAnswerByID(GUESTBOOK_ANSWER_IDS);
	}
}