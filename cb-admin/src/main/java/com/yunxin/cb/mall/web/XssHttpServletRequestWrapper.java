package com.yunxin.cb.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Auther: lxc
 * @Date: 2018/8/20 14:53
 * @Description:        过滤特殊字符
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 不进行特殊字符过滤的字段
     */
    final static String EXCLUDE_WORD = "password|settingContent|content|explainContent|messageContent";

    /**
     * 不进行特殊字符过滤的字段数组
     */
    final static String[] EXCLUDE_WORDS = EXCLUDE_WORD.split("\\|");

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        boolean q = false;      //匹配上则为true
        for (int j = 0;j<EXCLUDE_WORDS.length;j++){
            if(EXCLUDE_WORDS[j].equalsIgnoreCase(parameter)) {
                q = true;break;
            }
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            if(q){
                encodedValues[i] = values[i];
            }else {
                encodedValues[i] = cleanXSS(values[i]);
            }
        }
        return encodedValues;
    }

    /* 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        for (int j = 0;j<EXCLUDE_WORDS.length;j++){
            if(EXCLUDE_WORDS[j].equalsIgnoreCase(parameter)) {
                return value;
            }
        }

        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }
    private String cleanXSS(String value) {


        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
//        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
//        value = value.replaceAll("script", "");
//        value = value.replaceAll("[*]","["+"*]");
//        value = value.replaceAll("[+]","["+"+]");
//        value = value.replaceAll("[?]","["+"?]");


        // replace sql 这里可以自由发挥
        String[] values = value.split(" ");

        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|like|//|/|%|#";

        String[] badStrs = badStr.split("\\|");
        for (int i = 0;i<badStrs.length;i++){
            for (int j = 0;j<values.length;j++){
                if (values[j].equalsIgnoreCase(badStrs[i])){
                    values[j] = "";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<values.length;i++){
            if (i == values.length-1){
                sb.append(values[i]);
            } else {
                sb.append(values[i]+" ");
            }
        }

        value = sb.toString();

        return value;
    }

}