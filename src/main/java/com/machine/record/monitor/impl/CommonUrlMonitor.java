package com.machine.record.monitor.impl;

import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.PushMessageProxy;
import com.machine.record.monitor.inter.Monitor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service("commonUrlMonitor")
public class CommonUrlMonitor implements Monitor {

    @Autowired
    PushMessageProxy pushMessageProxy;
    @Resource
    MonitorToUserDao monitorToUserDao;

    @Override
    public String getTouser(String project) {
        return monitorToUserDao.getTouser(project);
    }

    @Override
    public String statistics(MonitorConfig monitorConfig) {
        String method = monitorConfig.getMethod();
        String url = monitorConfig.getUrl();
        String resultStatus = monitorConfig.getResultStatus();
        if (StringUtils.isBlank(method)) {
            monitorConfig.setMethod("get");
        }
        if (StringUtils.isBlank(url)) {
            return "url地址配置不正确";
        }
        if (StringUtils.isBlank(resultStatus)) {
            monitorConfig.setResultStatus("200");
        }
        if ("get".equals(monitorConfig.getMethod().toLowerCase())) {
            return get(monitorConfig);
        }
        if ("post".equals(monitorConfig.getMethod().toLowerCase())) {
            return post(monitorConfig);
        }

        return null;
    }

    public String get(MonitorConfig monitorConfig) {
        String message = "";
        //todo 发送可能的异常信息
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = null;

        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(monitorConfig.getUrl());
            // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (!monitorConfig.getResultStatus().contains("" + response.getStatusLine().getStatusCode())) {
                // 解析响应，获取数据
                message = monitorConfig.getMessage() + ",返回状态值为" + response.getStatusLine().getStatusCode();
            }
        } catch (IOException e) {
            message = "commonurl 调用接口异常";
            e.printStackTrace();
        } finally {
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
            return message;
        }

    }

    private String post(MonitorConfig monitorConfig) {
        String message = "";
        //todo 发送可能的异常信息
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建Httppost请求，相当于在浏览器输入地址
        HttpPost httpPost = null;

        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(monitorConfig.getUrl());
            // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (!monitorConfig.getResultStatus().contains("" + response.getStatusLine().getStatusCode())) {
                // 解析响应，获取数据
                message = monitorConfig.getMessage() + ",返回状态值为" + response.getStatusLine().getStatusCode();
            }
        } catch (IOException e) {
            message = "commonurl 调用接口异常";
            e.printStackTrace();
        } finally {
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
            return message;
        }
    }

    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {
        pushMessageProxy.pushMessage(message, touser, monitorConfig);
    }
}
