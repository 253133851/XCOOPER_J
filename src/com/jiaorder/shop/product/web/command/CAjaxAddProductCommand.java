package com.jiaorder.shop.product.web.command;

import com.jiaorder.shop.price.busi.ProductPriceMemberLevelBean;
import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.shop.product.web.util.LoadFormDataUtil;
import com.jiaorder.shop.productintro.busi.ProductIntroBean;
import com.jiaorder.shop.productintro.vo.ProductIntroVO;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.sys.file.busi.FileBean;
import com.jiaorder.sys.file.vo.FileVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunsai on 2016/3/16 - 15:28.
 */
public class CAjaxAddProductCommand implements Command {

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

    int serviceId = UserHelper.getServiceID(request);

    /**
     * 检查sku编码是否有重复
     */
    try {
      String allSkuCodes = getSkuCodes(request);
      if (StrUtil.isNotNull(allSkuCodes)) {
        String hasUsedSkuCode =
            ProductSkuBean.newInstance().checkHasUsedPrdSkuCode(serviceId, allSkuCodes);
        if (StrUtil.isNotNull(hasUsedSkuCode)) {
          validate.addError("商品规格编码：" + hasUsedSkuCode + " 已经被使用，请重新输入！");
        }
      }
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
  }

  @Override public String execute(RequestHelper helper, HttpServletRequest request)  {
    int serviceId = UserHelper.getServiceID(request);

    try {
      ProductVO product = new ProductVO();
      product.setPRD_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT"));

      /**
       * 设置product 的一般属性，既添加和修改都涉及到的一些属性
       */
      LoadFormDataUtil.setProductGenerAttrForAddOrModifyByRequest(product, request);

      /**
       * 设置商品只在添加的时候，才会设置的默认属性
       */
      setProductAttributeOnlyOnAdd(serviceId, product, request);


      /**
       * 添加商品
       */
      ProductBean.newInstance().addProduct(product);

      /**
       * 添加商品描述信息
       * TODO
       */
      saveProductIntro(serviceId, product.getPRD_ID(), request);

      /**
       * 保存所有的附件
       * TODO
       */
      saveUploadFiles(serviceId, product.getPRD_ID(), request);

      /**
       * 保存所有的sku以及和此sku对应的客户级别的定价表
       */
      saveAllSkuAndMemLevelPrice(serviceId, product.getPRD_ID(), request);

      /**
       * 添加操作日志
       */
      LogUtil.operLog(LogType.PRODUCT, "添加了新的商品", product.getPRD_ID(), request);
      return JsonResultUtil.ok();
    } catch (DataAccessException e) {
      e.printStackTrace();

      return JsonResultUtil.error();
    }
  }

  /**
   * 设置商品添加的时候比较默认的一些属性
   * @param serviceId
   * @param product
   * @param request
   */
  private void setProductAttributeOnlyOnAdd(int serviceId, ProductVO product,
      HttpServletRequest request) {

    product.setPRD_SPU(request.getParameter("spuCode"));
    product.setIS_DEL(-1);
    product.setSERVICE_ID(serviceId);
    product.setIS_SKU("true".equals(request.getParameter("setSku")) ? 1 : -1);
    product.setMIN_PRICE(StrUtil.convertToIntMoney(request.getParameter("price")));
    product.setMAX_PRICE(StrUtil.convertToIntMoney(request.getParameter("price")));

  }

  /**
   * 保存所有的附件信息
   * @param serviceId
   * @param prdId
   * @param request
   * @throws DataAccessException
   */
  private void saveUploadFiles(int serviceId, int prdId, HttpServletRequest request)
      throws DataAccessException {
    List<FileVO> uploadFileList = LoadFormDataUtil.getFileListByRequest(serviceId, prdId, request);
    FileBean.newInstance().addFile(uploadFileList);
  }



  /**
   * 保存所有的sku以及和其相关的价格关系
   * @param serviceId
   * @param prdId
   * @param request
   * @throws DataAccessException
   */
  private void saveAllSkuAndMemLevelPrice(int serviceId, int prdId, HttpServletRequest request)
      throws DataAccessException {

    String skuName1 = StrUtil.getNotNullStringValue(request.getParameter("skuName1"), "");
    String skuName2 = StrUtil.getNotNullStringValue(request.getParameter("skuName2"), "");
    String skuName3 = StrUtil.getNotNullStringValue(request.getParameter("skuName3"), "");

    int price = StrUtil.convertToIntMoney(request.getParameter("price"));
    int cost = StrUtil.convertToIntMoney(request.getParameter("cost"));

    /**
     * 加载所有的sku属性
     */
    List<ProductSkuVO> skuList = loadAllTmpSku(request);

    /**
     * 获取所有的客户级别对应的价格等属性
     */
    List<ProductPriceMemberLevelVO> productPriceMemberLevelList =
        LoadFormDataUtil.getProductPriceMemberLevelList(serviceId, request);

    /**
     * 对每个sku进行保存
     */
    for (ProductSkuVO skuVo : skuList) {
      skuVo.setSKU_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT_SKU"));
      skuVo.setSERVICE_ID(serviceId);
      skuVo.setIS_DEL(-1);
      skuVo.setCOST(cost);
      skuVo.setPRICE(price);
      skuVo.setPRD_ID(prdId);
      skuVo.setIS_SALE(ProductBean.SALE_STATE_YES);
      skuVo.setSKU_NAME1(skuName1);
      skuVo.setSKU_NAME2(skuName2);
      skuVo.setSKU_NAME3(skuName3);

      ProductSkuBean.newInstance().addProductSku(skuVo);
    }

    /**
     * 保存此sku对应的所有价格关系表
     */
    String skuIds = ProductSkuBean.newInstance().getSkuIdsByList(skuList);
    ProductPriceMemberLevelBean.newInstance()
        .addProductPriceMemberLevelBySkuIds(skuIds, productPriceMemberLevelList);
  }

  /**
   * 获取所有的sku编码，以逗号隔开
   * @param request
   * @return
   */
  private String getSkuCodes(HttpServletRequest request) {
    List<ProductSkuVO> tmpSkuList = loadAllTmpSku(request);
    StringBuilder sb = null;
    for (ProductSkuVO sku : tmpSkuList) {
      if (null == sb) {
        sb = new StringBuilder();
      } else {
        sb.append(",");
      }
      sb.append(sku.getPRD_SKU());
    }
    return sb == null ? "" : sb.toString();
  }

  /**
   * 保存 ProductIntro
   * @throws DataAccessException
   */
  private void saveProductIntro(int serviceId, int prd_id, HttpServletRequest request)
      throws DataAccessException {
    String productIntro = StrUtil.getNotNullStringValue(request.getParameter("productIntro"), "");

    ProductIntroVO intro = new ProductIntroVO();
    intro.setPRD_ID(prd_id);
    intro.setSERVICE_ID(serviceId);
    intro.setPRD_INTRO(productIntro);
    intro.setINTRO_ID(SeqNumHelper.getNewSeqNum("SHOP_PRODUCT_INTRO"));

    ProductIntroBean.newInstance().addProductIntro(intro);
  }




  private List<ProductSkuVO> loadAllTmpSku(HttpServletRequest request) {
    int skuLength = StrUtil.getNotNullIntValue(request.getParameter("skuLength"), 0);
    List<ProductSkuVO> list = new ArrayList<>();
    for (int i = 0; i < skuLength; i++) {
      ProductSkuVO sku = new ProductSkuVO();
      boolean isDel = "true".equals(request.getParameter("tags[" + i + "][isDel]"));

      if (!isDel) {
        sku.setPRD_SKU(request.getParameter("tags[" + i + "][skuCode]"));
        sku.setSKU_CONTENT1(request.getParameter("tags[" + i + "][tag1]"));
        sku.setSKU_CONTENT2(request.getParameter("tags[" + i + "][tag2]"));
        sku.setSKU_CONTENT3(request.getParameter("tags[" + i + "][tag3]"));
        sku.setBAR_CODE(request.getParameter("tags[" + i + "][barCode]"));
        sku.setIMG(request.getParameter("tags[" + i + "][imageUrl]"));
        list.add(sku);
      }

    }

    return list;
  }
}
