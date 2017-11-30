package com.seaboat.superrobot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Formatter;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>a util for wechat operation.</p>
 */
public final class WeChatUtil {

  private static String accessToken = null;
  private static Long cacheTime = null;

  private WeChatUtil() {}

  public static boolean checkSignature(String signature, String timestamp, String nonce)
      throws Exception {
    if (signature == null || timestamp == null || nonce == null) {
      return false;
    }
    String[] arr = new String[] {Constants.TOKEN, timestamp, nonce};
    // 将token、timestamp、nonce三个参数进行字典序排�?
    Arrays.sort(arr);
    StringBuilder content = new StringBuilder();
    for (String str : arr) {
      content.append(str);
    }
    String tmpStr;
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
    tmpStr = byteToHex(digest);
    return tmpStr.equals(signature);
  }

  public static String getAccessToken() throws Exception {
    long currentTime = System.currentTimeMillis() / 1000;
    if (!StringUtils.isEmpty(accessToken) && cacheTime != null
        && (currentTime - cacheTime) < 3600) {
      return accessToken;
    }
    String requestUrl = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APP_ID)
        .replace("APPSECRET", Constants.APP_SECRET);
    String result = HttpUtil.get(requestUrl);
    if (null == result) {
      return null;
    }
    JSONObject obj = JSON.parseObject(result);
    accessToken = obj.getString("access_token");
    cacheTime = currentTime;
    return accessToken;
  }

  public static String byteToHex(final byte[] hash) {
    Formatter formatter = new Formatter();
    for (byte b : hash) {
      formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
  }
}
