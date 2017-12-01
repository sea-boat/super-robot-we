package com.seaboat.superrobot.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.util.IOUtils;
import com.seaboat.superrobot.handle.WeChatMessageHandle;
import com.seaboat.superrobot.util.Constants;
import com.seaboat.superrobot.util.MessageUtil;
import com.seaboat.superrobot.util.WeChatUtil;
import com.seaboat.superrobot.vo.TextMessage;

/**
 * 
 * @author seaboat
 * @date 2017-11-30
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>the main controller.</p>
 */
@Controller
@RequestMapping("/weChat")
public class MainController {

  private static Logger LOGGER = Logger.getLogger(MainController.class);

  @Resource
  private WeChatMessageHandle eventMessageHandle;

  @Resource
  private WeChatMessageHandle textMessageHandle;

  @RequestMapping("receiveMessage")
  public void receiveMessage(String signature, String timestamp, String nonce, String echostr,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.setCharacterEncoding("UTF-8");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    response.setCharacterEncoding("UTF-8");
    try {
      Writer writer = response.getWriter();
      writer.write("success");
      writer.flush();
    } catch (IOException e) {
      LOGGER.error("Excetpion occurs when sending message to wechat", e);
    }
    String fromUserName = null;
    String toUserName = null;
    try {
      if (Constants.GET.equals(request.getMethod())) {
        if (WeChatUtil.checkSignature(signature, timestamp, nonce)) {
          writeText(echostr, response);
        } else {
          writeText("what's up?", response);
        }
      } else {
        String result;
        Map<String, String> parameters = MessageUtil.parseXml(request.getInputStream());
        fromUserName = parameters.get("FromUserName");
        toUserName = parameters.get("ToUserName");
        String msgType = parameters.get("MsgType");
        if ("event".equals(msgType)) {
          result = eventMessageHandle.processMessage(parameters);
        } else if ("text".equals(msgType)) {
          result = textMessageHandle.processMessage(parameters);
        } else {
          result = MessageUtil
              .ObjectToXml(new TextMessage(toUserName, fromUserName, "text is supported only."));
        }

        writeText(result, response);
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurs when receiving message from wechat", e);
      writeText(MessageUtil.ObjectToXml(new TextMessage(toUserName, fromUserName, "......")),
          response);
    }

  }

  private void writeText(String content, HttpServletResponse response) {
    Writer writer = null;
    try {
      response.setContentType("text/xml");
      response.setCharacterEncoding("UTF-8");
      writer = response.getWriter();
      writer.write(content);
      writer.flush();
    } catch (IOException e) {
      LOGGER.error("Excetpion occurs when sending message to wechat", e);
    } finally {
      IOUtils.close(writer);
    }
  }
}
