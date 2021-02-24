package com.machine.record.util;

import org.springframework.util.StringUtils;

public class StringConvert {
    public static String StringForSql(Object o){
        if(o == null){
            return "null";
        }
        String str = o.toString();
        if(StringUtils.hasText(str)){
            str = "'" + str + "'";
        }else {
            str = "null";
        }
        return str;
    }
}
