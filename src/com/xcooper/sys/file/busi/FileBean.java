package com.xcooper.sys.file.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.file.dao.FileDAO;
import com.xcooper.sys.file.vo.FileVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 项目文档Bean
 * @author zdk
 * 2016-03-28 19:31:58
 */
public class FileBean {

	Logger log = Logger.getLogger(FileBean.class);
	FileDAO dao;

	public FileBean(){
		dao = new FileDAO();
	}

	/**
	 * 添加项目文档
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void addFile(FileVO VO)throws DataAccessException {
		VO.setADD_DATETIME(DateUtil.getCurrTime());
		dao.addFile(VO);
	}

	/**
	 * 修改项目文档
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void modifyFile(FileVO VO)throws DataAccessException {
		dao.modifyFile(VO);
	}

	/**
	 * 删除项目文档
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void delFile(int ID)throws DataAccessException {
		dao.delFile(ID);
	}

	/**
	 * 批量删除项目文档
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void delFile(Collection coll)throws DataAccessException {
		dao.delFile(coll);
	}

	/**
	 * 根据SQL获取项目文档集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public Collection getFileColl(String sql)throws DataAccessException {
		return dao.getFileColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public FileVO getFileByID(int ID)throws DataAccessException {
		return dao.getFileByID(ID);
	}
}