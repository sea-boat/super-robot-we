package com.seaboat.superrobot.handle;

import org.springframework.stereotype.Service;

import com.seaboat.superrobot.util.MessageUtil;

import java.net.URLEncoder;
import java.util.Map;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>text message handling.</p>
 */
@Service
public class TextMessageHandle implements WeChatMessageHandle {

    @Override
    public String processMessage(final Map<String, String> parameters)
            throws Exception {
        String fromUserName = parameters.get("FromUserName");
        String toUserName = parameters.get("ToUserName");
        String content = parameters.get("Content");
        String info = URLEncoder.encode(content, "utf-8");
        String result = "";
        Object obj = MessageUtil.processTuRingResult(result, toUserName,
                fromUserName);
        return MessageUtil.ObjectToXml(obj);
    }
}
