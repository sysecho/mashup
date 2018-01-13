package com.sysecho.mashup.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String post(String url, String msg) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String body = null;
        HttpPost post = new HttpPost(url);
        try {
            //设置发送消息的参数
            //解决中文乱码的问题
            StringEntity entity = new StringEntity(msg,ContentType.APPLICATION_JSON);
            post.setEntity(entity);
        } catch (Exception e) {
            logger.error("post form组装异常", e);
        }

        body = invoke(httpclient, post);
        HttpClientUtils.closeQuietly(httpclient);
        return body;
    }

    public static String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String body = null;
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);
        HttpClientUtils.closeQuietly(httpclient);
        return body;
    }

    private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (Exception e) {
            logger.error("Http Response解析异常", e);
        }
        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
        } catch (Exception e) {
            logger.error("Http Request发送异常", e);
        }
        return response;
    }
}
