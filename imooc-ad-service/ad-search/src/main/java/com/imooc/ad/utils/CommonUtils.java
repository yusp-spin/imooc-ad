package com.imooc.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author spin
 * @date 2021/4/2214:53
 * @description: TODO
 */
@Slf4j
public class CommonUtils {

    public static <K,V> V getOrCreate(K key, Map<K,V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key,k->factory.get());
    }

    /**
    * @description: TODO 拼接字符串
    * @author yusp
    * @date 2021/4/24 19:12
    */
    public static String stringConcat(String... args) {
        StringBuffer result = new StringBuffer();
        for(String arg: args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static Date parseStringDate(String dateString) {
        try{
            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );
        }catch (ParseException ex) {
            log.error("parseStringDate error:{}",dateString);
            return null;
        }
    }
}
