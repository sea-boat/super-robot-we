package com.seaboat.superrobot.vo;

import java.util.Date;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>basic message vo.</p>
 */
public class BaseMessage {

  private String ToUserName;
  private String FromUserName;
  private long CreateTime;
  private String MsgType;

  public BaseMessage() {
    super();
  }

  public BaseMessage(String fromUserName, String toUserName) {
    super();
    FromUserName = fromUserName;
    ToUserName = toUserName;
    CreateTime = new Date().getTime();
  }

  public String getToUserName() {
    return ToUserName;
  }

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  public long getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(long createTime) {
    CreateTime = createTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }

}
