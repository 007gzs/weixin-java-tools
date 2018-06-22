package me.chanjar.weixin.open.bean.ma;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenMaCheckWxVerifyNicknameResult implements Serializable {
  private static final long serialVersionUID = -7299564219061386587L;
  private boolean hitCondition;
  private String wording;
}
