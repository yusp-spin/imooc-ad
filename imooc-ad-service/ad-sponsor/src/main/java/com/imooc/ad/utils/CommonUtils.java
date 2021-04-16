package com.imooc.ad.utils;

import com.imooc.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author spin
 * @date 2021/4/1613:39
 * @description: TODO 通过算法由 username 获取 token
 */
public class CommonUtils {
    private static String[] parsePatterns = {"yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"};

    /**
    * @description: TODO md5生成
    * @author yusp
    * @date 2021/4/16 14:11
    */
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String dateString) throws AdException {
        try {
            return DateUtils.parseDate(dateString,parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }

}
