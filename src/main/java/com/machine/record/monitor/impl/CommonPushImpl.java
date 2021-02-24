package com.machine.record.monitor.impl;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.entity.PushConfig;
import com.machine.record.monitor.inter.PushMessage;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service("commPush")
public class CommonPushImpl implements PushMessage {
    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {
        PushConfig pushConfig = monitorConfig.getPushConfig();
        if (pushConfig == null) {
            return;
        }
        String method = pushConfig.getPushMethod();
        if ("get".equals(method.toLowerCase())) {
            pushWithMethodGet(message, touser, pushConfig);
        } else if ("post".equals(method.toLowerCase())) {
            pushWithMethodPost(message, touser, pushConfig);
        }
    }

    private void pushWithMethodGet(String message, String touser, PushConfig pushConfig) {
        String url = "";
        String pushurl = pushConfig.getPushUrl();
        //todo 发送可能的异常信息
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = null;

        CloseableHttpResponse response = null;
        try {
            url += pushurl + "?warningType=2&warningFlag=2&touser=" + touser + "&content=" + URLEncoder.encode(message, "UTF-8");
            httpGet = new HttpGet(url);
            // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应，获取数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            // 关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 关闭浏览器
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void pushWithMethodPost(String message, String touser, PushConfig pushConfig) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(pushConfig.getPushUrl());
        List params = new ArrayList();
        params.add(new BasicNameValuePair("warningType", "2"));
        params.add(new BasicNameValuePair("warningFlag", "2"));
        params.add(new BasicNameValuePair("touser", touser));
        params.add(new BasicNameValuePair("content", message));
        try {
            HttpEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(httpEntity);
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
