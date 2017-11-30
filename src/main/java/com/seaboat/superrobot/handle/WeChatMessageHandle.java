package com.seaboat.superrobot.handle;

import java.util.Map;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>message handling interface.</p>
 */
public interface WeChatMessageHandle {

  String processMessage(Map<String, String> parameters) throws Exception;
  
}
