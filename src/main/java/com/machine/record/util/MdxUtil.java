package com.machine.record.util;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * @author gehongzhi
 * @Description 组装mdx工具类
 * @date 2019-7-9 09:06:14
 */
public class MdxUtil {
    private static final Log logger = LogFactory.getLog(MdxUtil.class);

    /**
     * @param jsonParam    json格式
     * @param showCodeFlag 是否显示指标编码的标识，true显示编码，false不显示编码
     * @param allFlag      是否显示全部数据的标识，true显示all，false不显示all
     * @Description 方法描述：按Map<日期，Map<指标，值>>的格式存储后返回前台
     * @author gehongzhi
     * @date 2018年7月11日
     */
    public static Map<String, Map<String, String>> dealWXDimFieldValueJson(String jsonParam, boolean showCodeFlag,
                                                                           boolean allFlag) {
        LinkedHashMap<String, Map<String, String>> result = new LinkedHashMap<>();
        if (StringUtils.isBlank(jsonParam)) {
            return result;
        }
        Gson gson = new Gson();
        List<Object> nodesList = new ArrayList<>();
        try {
            nodesList = gson.fromJson(jsonParam, nodesList.getClass());
        } catch (JsonSyntaxException e) {
            return result;
        }

        for (Object obj : nodesList) {
            Map<String, String> tempMap = (Map<String, String>) obj;
            String temp = tempMap.get("0");
            if (temp == null) {
                continue;
            }
            //去除all
            if (temp.toLowerCase().contains("[all]|all") && !allFlag) {
                continue;
            }
            String tempKey = tempMap.get("0");
            //是否显示编码
            if (!showCodeFlag) {
                tempKey = tempMap.get("0").split("\\|")[1];
            }
            for (Map.Entry<String, String> entryMap : tempMap.entrySet()) {
                Map<String, String> tMap = new HashMap<>();
                String value = entryMap.getValue();
                //两种正常的特殊情况,先特殊处理
                if (value.startsWith(".")) {
                    value = 0 + value;
                } else if (value.startsWith("-.")) {
                    value = "-0" + value.substring(1);
                }
                tMap.put(entryMap.getKey(), value);
                result.put(tempKey, tMap);
            }
        }
        return result;
    }

}
