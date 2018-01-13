package com.sysecho.mashup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sysecho.mashup.utils.HttpUtils;

/**
* 类描述
* @author xiaofei.xian
* 日期：2018年1月13日 上午11:19:22
*/

@Controller
@RequestMapping("wechat")
public class WeChatController {

  @RequestMapping("article")
  @ResponseBody
  public Object article() {
    String url = "http://v.juhe.cn/weixin/query?key=0782b42fe35f6b551a2508d773d413fd";
    return HttpUtils.get(url);
  }
}
