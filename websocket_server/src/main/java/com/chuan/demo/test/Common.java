package com.chuan.demo.test;

import java.util.Date;
import java.util.Random;

class Common {
    /**
     * 版本号
     */
    private final static String GRANTTYPE = "v2.0";

    /**
     * 获取随机码
     */
    static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    /**
     * 获取当前时间
     * @return 返回当前时间
     */
    private static Long getCurrentTimeStamp(){
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 获取有效期
     * @param time 时间戳
     * @return 有效期
     */
    static Long getExpired(long time){
        return getCurrentTimeStamp()+time;
    }

    static String getUrl(String apiId,String body, Long expired,
                         String id, String random, String secretId,
                         String uid){
        return "apiId="+apiId+"&body="+body+
                "&currentTimeStamp="+getCurrentTimeStamp()+
//                "&currentTimeStamp="+1565335110791L+
                "&expired="+expired+"&grant_type="+GRANTTYPE+
                "&id="+id+"&random="+random+
                "&secretId="+secretId+"&uid="+uid;
    }

}
