package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenMaQueryNicknameResult implements Serializable {
  private static final long serialVersionUID = 5219068986070535076L;
  private String nickname;
  private int auditStat;
  private String failReason;
  private long createTime;
  private long auditTime;
}
