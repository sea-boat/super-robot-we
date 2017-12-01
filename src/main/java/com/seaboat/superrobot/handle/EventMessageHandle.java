package com.seaboat.superrobot.handle;

import org.springframework.stereotype.Service;

import com.seaboat.superrobot.util.Constants;
import com.seaboat.superrobot.util.MessageUtil;
import com.seaboat.superrobot.vo.TextMessage;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>event message handling.</p>
 */
@Service
public class EventMessageHandle implements WeChatMessageHandle {

  @Override
  public String processMessage(final Map<String, String> parameters) {
    String eventType = parameters.get("Event");
    String fromUserName = parameters.get("FromUserName");
    String toUserName = parameters.get("ToUserName");
    if (Constants.REQ_SUBSCRIBE_TYPE.equals(eventType)) {
      String text = "~";
      try {
        text = new String(
            "欢迎找我聊天. \n 创造我的人是seaboat. 可以关注他的公众号《远洋号》。\n 那里有“分布式”、“机器学习”、“深度学习”、“NLP”、“Java深度”、“Java并发核心”、“JDK源码”、“Tomcat内核”等"
                .getBytes(),
            "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return MessageUtil.ObjectToXml(new TextMessage(toUserName, fromUserName, text));
    } else if (Constants.REQ_UNSUBSCRIBE_TYPE.equals(eventType)) {

    } else if (Constants.REQ_EVENT_TYPE.equals(eventType)) {

    }
    return "";
  }
}
