package com.seaboat.superrobot.vo;

import com.seaboat.superrobot.util.Constants;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>text message vo.</p>
 */
public class TextMessage extends BaseMessage {

  /** 内容 **/
  private String Content;

  public TextMessage(String fromUserName, String toUserName, String content) {
    super(fromUserName, toUserName);
    super.setMsgType(Constants.RESP_TEXT_TYPE);
    this.Content = content;
  }

  public TextMessage() {
    super();
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

}
