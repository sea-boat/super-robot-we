package com.seaboat.superrobot.handle;

import org.springframework.stereotype.Service;

import com.seaboat.superrobot.util.Constants;
import com.seaboat.superrobot.util.MessageUtil;
import com.seaboat.superrobot.vo.TextMessage;

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
    String toUserName = parameters.get("toUserName");
    if (Constants.REQ_SUBSCRIBE_TYPE.equals(eventType)) {
      return MessageUtil.ObjectToXml(new TextMessage(toUserName, fromUserName, "thanks for following me."));
    } else if (Constants.REQ_UNSUBSCRIBE_TYPE.equals(eventType)) {

    } else if (Constants.REQ_EVENT_TYPE.equals(eventType)) {

    }
    return "";
  }
}
