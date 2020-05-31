package edu.jlxy.lzh.utils;

import java.util.Map;

public class BeanUtils {
    /**
     * 把map中的值注入到Javabean中
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> T beanparams(Map map,T bean){
        try {
            org.apache.commons.beanutils.BeanUtils.populate(bean,map);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换为int类型
     * @param str
     * @param value 返回的默认值
     * @return
     */
    public static int parseInt(String str,int value){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }
        return value;
    }
}
