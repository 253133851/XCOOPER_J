package com.jiaorder.shop.price.busi;

import com.jiaorder.shop.price.dao.ProductPriceMemberLevelDAO;
import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 商品价格会员等级对应Bean
 * @author sunsai
 * 2016-03-18 14:50:50
 */
public class ProductPriceMemberLevelBean {

	Logger log = Logger.getLogger(ProductPriceMemberLevelBean.class);
	ProductPriceMemberLevelDAO dao;

	private static ProductPriceMemberLevelBean bean = null;

	private ProductPriceMemberLevelBean(){
		dao = new ProductPriceMemberLevelDAO();
	}

	//单例
	public static ProductPriceMemberLevelBean newInstance() {
		if (null == bean) {
			synchronized (ProductPriceMemberLevelBean.class) {
				if (null == bean) {
					bean = new ProductPriceMemberLevelBean();
				}
			}
		}
		return bean;
	}

	/**
	 * 添加商品价格会员等级对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void addProductPriceMemberLevel(ProductPriceMemberLevelVO VO)throws DataAccessException{
		if (VO.getID() < 1) {
			//id这种 如果之前不需要还是不用先设置的比较好，如果前面有一处拼错了呢？ 或者提供表名的常量？
			VO.setID(SeqNumHelper.getNewSeqNum(ProductPriceMemberLevelVO.TABLE_NAME));
		}
		dao.addProductPriceMemberLevel(VO);
	}

	/**
	 * 批量添加ProductPriceMemberLevelVO
	 * @param productPriceMemberLevelVOList
	 * @throws DataAccessException
   */
	public void addProductPriceMemberLevel(List<ProductPriceMemberLevelVO> productPriceMemberLevelVOList) throws DataAccessException {
		for(ProductPriceMemberLevelVO vo : productPriceMemberLevelVOList) {
			addProductPriceMemberLevel(vo);
		}
	}

	/**
	 * 修改商品价格会员等级对应
	 * @param VO
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void modifyProductPriceMemberLevel(ProductPriceMemberLevelVO VO)throws DataAccessException{
		dao.modifyProductPriceMemberLevel(VO);
	}

	/**
	 * 删除商品价格会员等级对应
	 * @param ID
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void delProductPriceMemberLevel(int serviceId, int ID)throws DataAccessException{
		dao.delProductPriceMemberLevel(ID);
	}

	/**
	 * 批量删除商品价格会员等级对应
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public void delProductPriceMemberLevel(Collection coll)throws DataAccessException{
		dao.delProductPriceMemberLevel(coll);
	}

	/**
	 * 根据SQL获取商品价格会员等级对应集合
	 * @param sql
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public Collection getProductPriceMemberLevelColl(String sql)throws DataAccessException{
		return dao.getProductPriceMemberLevelColl(sql);
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author sunsai 2016-03-18 14:50:50
	 */
	public ProductPriceMemberLevelVO getProductPriceMemberLevelByID(int ID)throws DataAccessException{
		return dao.getProductPriceMemberLevelByID(ID);
	}

	public List<ProductPriceMemberLevelVO> getProductPriceMemberLevelCollBySkuId(int serviceId, int skuId) throws DataAccessException {

		SqlHelper sh = new SqlHelper();
		sh.setTable("PRODUCT_PRICE_MEMBER_LEVEL");
		sh.setSelectColumn("*");
		sh.setWhereForInt("SERVICE_ID","=", serviceId);
		sh.setWhereForInt("SKU_ID","=", skuId);
		String sql = sh.getSQL(sh.getSelectSQL());

		return dao.getProductPriceMemberLevelColl(sql);
	}

  /**
	 * 根据skuIdS 删除等于skuId的所有数据
	 *
	 * @param serviceId
	 * @param skuIds
	 * @throws DataAccessException
   */
	public void delProductPricememberLevelBySkuIds(int serviceId, String skuIds) throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setTable(ProductPriceMemberLevelVO.TABLE_NAME);

		sh.setWhereForInt("SERVICE_ID", "=", serviceId);

		sh.setOrGroupForInt("SKU_ID", "=", skuIds, true);

		try {
			sh.delete(ResourceManager.getConnection(), "根据skuIds删除");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

  /***
	 *  批量添加用户级别和商品的价格对应表
	 * @param skuIds 该商品下面的所有sku的id
	 * @param productPriceMemberList 针对该商品下所有sku默认的价格关系
   */
	public void addProductPriceMemberLevelBySkuIds(String skuIds,List<ProductPriceMemberLevelVO> productPriceMemberList) throws DataAccessException{
		if (StrUtil.isNull(skuIds)) {
			return;
		}

		String[] skuId = null;

		if (skuIds.contains(",")) {
			skuId = skuIds.split(",");
		} else {
			skuId = new String[]{skuIds};
		}


		//TODO  可以用批处理插入数据 ，减少读写次数
		for (int i = 0; i < skuId.length; i++) {

			int id = StrUtil.getNotNullIntValue(skuId[i]);

			for (ProductPriceMemberLevelVO vo : productPriceMemberList) {
				vo.setSKU_ID(id);
				vo.setID(SeqNumHelper.getNewSeqNum("PRODUCT_PRICE_MEMBER_LEVEL"));
				addProductPriceMemberLevel(vo);
			}
		}



	}


}