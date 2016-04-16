package com.jiaorder.shop.product.web.command;

import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.shop.product.web.util.LoadFormDataUtil;
import com.jiaorder.shop.productintro.busi.ProductIntroBean;
import com.jiaorder.shop.productintro.vo.ProductIntroVO;
import com.jiaorder.shop.sku.busi.ProductSkuBean;
import com.jiaorder.shop.sku.vo.ProductSkuVO;
import com.jiaorder.sys.file.FileTye;
import com.jiaorder.sys.file.busi.FileBean;
import com.jiaorder.sys.file.vo.FileVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunsai on 2016/3/21 - 18:40.
 */
public class CAjaxModifyProductCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws ServletException, BusinessRuleException, DataAccessException, SysException {


    int prdId = StrUtil.getNotNullIntValue(request.getParameter("prdId"),0);

    int serviceId = UserHelper.getServiceID(request);

    ProductVO product = ProductBean.newInstance().getProductVOById(serviceId, prdId);

    if (null == product) {
      return JsonResultUtil.instance()
          .addCode(JsonResultUtil.ERROR)
          .addMsg("出错了，请刷新后再试").json();
    }


    //setProductAttributeByRequest(request, product);

    LoadFormDataUtil.setProductGenerAttrForAddOrModifyByRequest(product, request);

    ProductBean.newInstance().updateProduct(serviceId, product);

    List<ProductSkuVO> newSkuList = loadSkuListByRequest(request);

    List<ProductSkuVO> oldSkuList = ProductSkuBean.newInstance().getSkuCollByProductIDS(serviceId, String.valueOf(prdId));

    Map<Integer, ProductSkuVO> oldSkuMap = ProductSkuBean.newInstance().convertListToMap(oldSkuList);

    for(ProductSkuVO newSku : newSkuList) {

      ProductSkuVO old = oldSkuMap.get(newSku.getSKU_ID());

      if (null != old) {
        old.setBAR_CODE(newSku.getBAR_CODE());
        old.setIS_SALE(newSku.getIS_SALE());
        old.setIS_DEL(newSku.getIS_DEL());

        if (old.getIS_DEL() == 1) {
          old.setDEL_DATETIME(DateUtil.getCurrTime());
        }

        ProductSkuBean.newInstance().updateProductSku(serviceId, old);
      }

    }


    /**
     * 添加商品描述信息
     * TODO
     */
    //updateProductIntro(serviceId, product.getPRD_ID(), request);


    String productIntro = request.getParameter("productIntro");

    ProductIntroVO intro = ProductIntroBean.newInstance().getProductIntroByProductId(serviceId, prdId);

    intro.setPRD_INTRO(productIntro);

    ProductIntroBean.newInstance().updateProductIntro(serviceId, intro);

    //TODO更新附件地址
    //以后会重试写个增量更新的方法？
    //先删除之前所有的附件
    FileBean.newInstance().delFileByTargetIdAndType(serviceId, prdId, FileTye.PRODUCT);

    //再保存现在最新的附件
    saveUploadFiles(serviceId, product.getPRD_ID(), request);

    return JsonResultUtil.ok();
  }


  /**
   * 保存所有的附件信息
   * @param serviceId 服务id
   * @param prdId 商品id
   * @param request httpRequest
   * @throws DataAccessException
   */
  private void saveUploadFiles(int serviceId, int prdId, HttpServletRequest request)
      throws DataAccessException {
    List<FileVO> uploadFileList = LoadFormDataUtil.getFileListByRequest(serviceId, prdId, request);
    FileBean.newInstance().addFile(uploadFileList);
  }


  private List<ProductSkuVO> loadSkuListByRequest(HttpServletRequest request) {

    int skuListLength = StrUtil.getNotNullIntValue(request.getParameter("skuInfoLength"), 0);

    List<ProductSkuVO> skuList = new ArrayList<>();


    for (int i = 0; i < skuListLength; i++) {

      int skuId = StrUtil.getNotNullIntValue(request.getParameter("skuInfo["+ i +"][skuId]"), 0);
      int isSale = StrUtil.getNotNullIntValue(request.getParameter("skuInfo["+ i +"][isSale]"), 0);
      int isDel = StrUtil.getNotNullIntValue(request.getParameter("skuInfo["+ i +"][isDel]"), 0);
      String barCode = request.getParameter("skuInfo[" + i + "][barCode]");

      ProductSkuVO sku = new ProductSkuVO();

      sku.setSKU_ID(skuId);
      sku.setIS_SALE(isSale);
      sku.setIS_DEL(isDel);
      sku.setBAR_CODE(barCode);

      skuList.add(sku);
    }

    return skuList;
  }


  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate)
      throws RuleException {

  }

}
