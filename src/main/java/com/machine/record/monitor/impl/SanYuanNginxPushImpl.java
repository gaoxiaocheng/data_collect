package com.machine.record.monitor.impl;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.entity.PushConfig;
import com.machine.record.monitor.inter.PushMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service("sanYuanNginxPush")
public class SanYuanNginxPushImpl implements PushMessage {

    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {

        PushConfig pushConfig = monitorConfig.getPushConfig();


        //todo 发送可能的异常信息
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = null;

        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(pushConfig.getPushUrl() + "?touser=" + touser +
                    "&send_type=1&content=" + URLEncoder.encode(message, "UTF-8"));
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
}
