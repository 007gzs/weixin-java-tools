package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.ma.*;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
/* package */ class WxOpenMaServiceImpl extends WxMaServiceImpl implements WxOpenMaService {
  private static final String GET_ACCOUNT_BASIC_INFO_URL = "https://api.weixin.qq.com/cgi-bin/ma/getaccountbasicinfo";
  private static final String SET_NICKNAME_URL = "https://api.weixin.qq.com/wxa/setnickname";
  private static final String QUERY_NICKNAME_URL = "https://api.weixin.qq.com/wxa/api_wxa_querynickname";
  private static final String CHECK_WX_VERIFY_NICKNAME_URL = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname";
  private static final String MODIFY_HEAD_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/ma/modifyheadimage";
  private static final String MODIFY_SIGNATURE_URL = "https://api.weixin.qq.com/cgi-bin/ma/modifysignature";
  private static final String COMPONENTRE_BIND_ADMIN_JUMP_URL = "https://mp.weixin.qq.com/wxopen/componentrebindadmin?appid=%s&component_appid=%s&redirect_uri=%s";
  private static final String COMPONENTRE_BIND_ADMIN_URL = "https://api.weixin.qq.com/cgi- bin/ma/componentrebindadmin";
  private static final String GET_ALL_CATEGORIES_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories";
  private static final String ADD_CATEGORY_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
  private static final String DELETE_CATEGORY_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";
  private static final String GET_CATEGORY_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
  private static final String MODIFY_CATEGORY_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/modifycategory";
  private static final String GET_WXA_SEARCH_STATUS_URL = "https://api.weixin.qq.com/wxa/getwxasearchstatus";
  private static final String CHANGE_WXA_SEARCH_STATUS_URL = "https://api.weixin.qq.com/wxa/changewxasearchstatus";


  private WxOpenComponentService wxOpenComponentService;
  private WxMaConfig wxMaConfig;
  private String appId;

  public WxOpenMaServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    return wxOpenComponentService.miniappJscode2Session(appId, jsCode);
  }
  @Override
  public WxMaConfig getWxMaConfig() {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  @Override
  public WxOpenMaAccountBasicInfo getAccountBasicInfo() throws WxErrorException {
    String result = get(GET_ACCOUNT_BASIC_INFO_URL, null);
    return WxGsonBuilder.create().fromJson(result, WxOpenMaAccountBasicInfo.class);
  }

  @Override
  public WxOpenMaSetNicknameResult setNickname(String nickName, String idCard, String license, List<String> namingOtherStuffs) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("nick_name", nickName);
    jsonObject.addProperty("id_card", idCard);
    jsonObject.addProperty("license", license);
    if(namingOtherStuffs != null){
      int i = 1;
      for(String namingOtherStuff: namingOtherStuffs){
        if(i > 5){
          throw new RuntimeException("namingOtherStuffs is too long");
        }
        jsonObject.addProperty("naming_other_stuff_" + i, namingOtherStuff);
        i++;
      }
    }
    String result = post(SET_NICKNAME_URL, jsonObject.toString());
    return WxGsonBuilder.create().fromJson(result, WxOpenMaSetNicknameResult.class);
  }

  @Override
  public WxOpenMaQueryNicknameResult queryNickname(long auditId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("audit_id", auditId);
    String result = post(QUERY_NICKNAME_URL, jsonObject.toString());
    return WxGsonBuilder.create().fromJson(result, WxOpenMaQueryNicknameResult.class);
  }

  @Override
  public WxOpenMaCheckWxVerifyNicknameResult checkWxVerifyNickname(String nickName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("nick_name", nickName);
    String result = post(CHECK_WX_VERIFY_NICKNAME_URL, jsonObject.toString());
    return WxGsonBuilder.create().fromJson(result, WxOpenMaCheckWxVerifyNicknameResult.class);
  }

  @Override
  public void modifyHeadImage(String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("head_img_media_id", headImgMediaId);
    jsonObject.addProperty("x1", x1);
    jsonObject.addProperty("y1", y1);
    jsonObject.addProperty("x2", x2);
    jsonObject.addProperty("y2", y2);
    post(MODIFY_HEAD_IMAGE_URL, jsonObject.toString());
  }

  @Override
  public void modifySignature(String signature) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("signature", signature);
    post(MODIFY_SIGNATURE_URL, jsonObject.toString());
  }

  @Override
  public String getcomponentreBindAdminUrl(String redirectUri) throws WxErrorException {
    return String.format(COMPONENTRE_BIND_ADMIN_JUMP_URL, appId,
      wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId(), URIUtil.encodeURIComponent(redirectUri));
  }

  @Override
  public void componentreBindAdmin(String taskId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("taskid", taskId);
    post(COMPONENTRE_BIND_ADMIN_URL, jsonObject.toString());
  }

  @Override
  public WxOpenMaAllCategorieResult getAllCategories() throws WxErrorException {
    String result = get(GET_ALL_CATEGORIES_URL, null);
    return WxGsonBuilder.create().fromJson(result, WxOpenMaAllCategorieResult.class);
  }
  private String makeAddOrModifyCategory(long first, long second, Map<String, String> certicates){
    JsonObject jsonObject;
    JsonArray certicateArray = new JsonArray();
    for (Map.Entry<String, String> entry:certicates.entrySet()) {
      jsonObject = new JsonObject();
      jsonObject.addProperty(entry.getKey(), entry.getValue());
      certicateArray.add(jsonObject);
    }
    jsonObject = new JsonObject();
    jsonObject.add("certicates", certicateArray);
    jsonObject.addProperty("first", first);
    jsonObject.addProperty("second", second);
    return jsonObject.toString();
  }
  @Override
  public void addCategory(long first, long second, Map<String, String> certicates) throws WxErrorException {
    post(ADD_CATEGORY_URL, makeAddOrModifyCategory(first, second, certicates));
  }

  @Override
  public void deleteCategory(long first, long second) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("first", first);
    jsonObject.addProperty("second", second);
    post(DELETE_CATEGORY_URL, jsonObject.toString());
  }

  @Override
  public WxOpenMaGetCategorieResult getCategory() throws WxErrorException {
    String result = get(GET_CATEGORY_URL, null);
    return WxGsonBuilder.create().fromJson(result, WxOpenMaGetCategorieResult.class);
  }

  @Override
  public void modifyCategory(long first, long second, Map<String, String> certicates) throws WxErrorException {
    post(MODIFY_CATEGORY_URL, makeAddOrModifyCategory(first, second, certicates));
  }

  @Override
  public int getSearchStatus() throws WxErrorException {
    String result = get(GET_WXA_SEARCH_STATUS_URL, null);
    JsonObject jsonObject = WxGsonBuilder.create().fromJson(result, JsonObject.class);
    return jsonObject.get("status").getAsInt();
  }
  @Override
  public void changeSearchStatus(int status) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("status", status);
    post(CHANGE_WXA_SEARCH_STATUS_URL, jsonObject.toString());
  }
}
