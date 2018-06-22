package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.*;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenMaService extends WxMaService {

  /**
   * 获取帐号基本信息
   */
  WxOpenMaAccountBasicInfo getAccountBasicInfo() throws WxErrorException;

  /**
   * 小程序名称设置及改名
   * @param nickName 昵称
   * @param idCard 身份证照片–临时素材mediaid
   * @param license 组织机构代码证或营业执照–临时素材mediaid
   * @param namingOtherStuffs 其他证明材料---临时素材 mediaid 最多5个
   */
  WxOpenMaSetNicknameResult setNickname(String nickName, String idCard, String license, List<String> namingOtherStuffs) throws WxErrorException;

  /**
   * 小程序改名审核状态查询
   *
   * @param auditId 审核单id
   */
  WxOpenMaQueryNicknameResult queryNickname(long auditId) throws WxErrorException;

  /**
   * 微信认证名称检测
   */
  WxOpenMaCheckWxVerifyNicknameResult checkWxVerifyNickname(String nickName) throws WxErrorException;

  /**
   * 修改头像
   * @param headImgMediaId 头像素材media_id
   * @param x1 裁剪框左上角x坐标（取值范围：[0, 1]）
   * @param y1 裁剪框左上角y坐标（取值范围：[0, 1]）
   * @param x2 裁剪框右下角x坐标（取值范围：[0, 1]）
   * @param y2 裁剪框右下角y坐标（取值范围：[0, 1]）
   */
  void modifyHeadImage(String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException;

  /**
   * 修改功能介绍
   */
  void modifySignature(String signature) throws WxErrorException;

  /**
   * 从第三方平台跳转至微信公众平台授权注册页面
   *
   * @param redirectUri 新管理员信息填写完成点击提交后，将跳转到该地址
   *                    (注：Host需和第三方平台在微信开放平台上面填写的登录授权的发起页域名一致)
   */
  String getcomponentreBindAdminUrl(String redirectUri) throws WxErrorException;

  /**
   * 第三方平台调用快速注册API完成管理员换绑。
   *
   * @param taskId 换绑管理员任务序列号(公众平台最终点击提交回跳到第三方平台时携带)
   */
  void componentreBindAdmin(String taskId) throws WxErrorException;

  /**
   * 获取账号可以设置的所有类目
   */
  WxOpenMaAllCategorieResult getAllCategories() throws WxErrorException;

  /**
   * 添加类目
   * @param first 一级类目ID
   * @param second 二级类目ID
   * @param certicates key: 资质名称 value: 资质图片 media_id
   */
  void addCategory(long first, long second, Map<String, String> certicates) throws WxErrorException;

  /**
   * 删除类目
   * @param first 一级类目ID
   * @param second 二级类目ID
   */
  void deleteCategory(long first, long second) throws WxErrorException;

  /**
   * 获取账号已经设置的所有类目
   */
  WxOpenMaGetCategorieResult getCategory() throws WxErrorException;

  /**
   * 修改类目
   * @param first 一级类目ID
   * @param second 二级类目ID
   * @param certicates key: 资质名称 value: 资质图片 media_id
   */
  void modifyCategory(long first, long second, Map<String, String> certicates) throws WxErrorException;

  /**
   * 查询小程序当前隐私设置（是否可被搜索）
   */
  int getSearchStatus() throws WxErrorException;

  /**
   * 设置小程序隐私设置（是否可被搜索）
   *
   * @param status 1表示不可搜索，0表示可搜索
   */
  void changeSearchStatus(int status) throws WxErrorException;
}
