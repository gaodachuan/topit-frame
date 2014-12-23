package com.topit.frame.common.util;


/**
 * @author Jiangdefu
 * 字符串通用处理的工具类。
 */
public class StringUtil {
	/**
	 * @param str 待格式化的字符串内容，如“你好,{0}”，其中括号及内部的内容索引，将被替换成指定的文本。
	 * @param args 需要替换中括号的取值对象数组。
	 * @return 返回格式化后的字符串。
	 */
	public static String format(String str,Object ... args)  
    {  
        //这里用于验证数据有效性  
        if(str==null||"".equals(str))  
            return "";  
        if(args.length==0)  
        {  
            return str;  
        } 
        String result=str;  
        //这里的作用是只匹配{}里面是数字的子字符串  
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\{(\\d+)\\}");  
        java.util.regex.Matcher m = p.matcher(str);  
  
        while(m.find())  
        {  
            //获取{}里面的数字作为匹配组的下标取值  
            int index=Integer.parseInt(m.group(1));  
            //这里得考虑数组越界问题，{1000}也能取到值么？？ 
            if(index<args.length)  
            {  
                //替换，以{}数字为下标，在参数数组中取值  
                result=result.replace(m.group(),args[index].toString());  
            }
        }  
        return result;  
    }  
}
