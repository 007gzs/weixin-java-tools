package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenMaAllCategorieResult implements Serializable {
  private static final long serialVersionUID = -3133864192169127945L;
  List<WxOpenMaCategorie> categories;
  @Data
  public static class  WxOpenMaCategorie implements Serializable {
    private static final long serialVersionUID = -5973962647474207880L;
    private List<Integer> children;
    private long father;
    private long id;
    private int level;
    private String  name;
    private int sensitiveType;
    private List<WxOpenMaCategorieQualify> qualify;
  }
  @Data
  public static class  WxOpenMaCategorieQualify implements Serializable {
    private static final long serialVersionUID = -2124447136264883085L;
    private String name;
    private String url;
  }
}
