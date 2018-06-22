package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenMaSetNicknameResult implements Serializable {

  private static final long serialVersionUID = 3250288943995067872L;
  private String wording;
  private long auditId;
}
