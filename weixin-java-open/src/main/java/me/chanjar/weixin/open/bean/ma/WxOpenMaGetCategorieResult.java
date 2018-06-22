package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenMaGetCategorieResult implements Serializable {
  private static final long serialVersionUID = -7236139516311772349L;
  private List<WxOpenMaCategorie> categories;
  private int limit;
  private int quota;
  private int categoryLimit;
  @Data
  public static class WxOpenMaCategorie implements Serializable {
    private static final long serialVersionUID = -8350647215663510201L;
    private long first;
    private long second;
    private String firstName;
    private String secondName;
    private int audit_status;
    private String audit_reason;
  }
}
