package com.chuan.demo.test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Demo {

    /**
     * 例如查询直播详情接口
     *
     * 接口地址：
     *
     * http://api.migucloud.cm/l2/live/getChannel
     *
     * 公共参数为：
     *
     * uid 109
     *
     * secretId 123456789
     *
     * random 87654321
     *
     * grant_type v2.0 固定值
     *
     * currentTimeStamp 1565335110791
     *
     * expired 1565335710791
     *
     * apiId 001|002
     *
     * body GET固定值 %5B%5D
     * POST请求为body体urlEncode编码
     *
     * 业务参数为：
     *
     * id 12345_xxx
     *
     * 加密秘钥为：
     *
     * $secretKey IamsecretKey
     *
     */
    public static void main(String[] args) {
        //uid
        /*String uid = "4550";
        //apiId
        String apiId = "200001|200002|200003|200005|200007|200004|200010|200006|200011|200009|200012|200013|200014|200015|200016|200017|200018|200019|200020|200021|200022|200023|200024|200025|200026|200027|200028|200029|200031|200030|200032|200033|200034|200035|200036|200037|200038|200040|200039|200041|200042|200043|200044|200045|200046|200047|200048|200049|200050|200051|200052|200053|200054|200056|200055|200057|200058|200059|200060|200061|200063|200062|200064|200065|200066|200067|200068|200069|200008|201001|201002|201003|201004|201005|201006|201007|201008|201009|201010|201011|201012|201013|201014|201015|201017|201016|202001|202002|202003|202004";
        //body GET
        String body = "%5B%5D";
        //如果请求为POST，需要报文encode编码，默认使用utf-8编码方式
        try {
            body = URLEncoder.encode("{\"vid\":\"005i4b5Sp6zXUI2PKQGY8C\"}","utf-8");
            System.out.println(body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //时间戳
        Long expired = Common.getExpired(86400000);
//        Long expired = 1565335710791L;
        //业务ID
        String id = "12345_xxx";
        //获取8位随机码
//        String random = Common.getRandomString(8);
        String random = "87654321";
        //加密密钥ID
        String secretId = "1afd972790ba00b8c8b5ccd2aede246b";
        //密钥串
        String secretKey = "e3a140d3a5087b1cbe67c97bd7d7d505";
        //url串
        String url = Common.getUrl(apiId,body,expired,id,random, secretId,uid);
        //生成token
        String token = getToken(secretKey,url);
        //输出token内容
        System.out.println(token);*/

        String a="{\"startTime\":\"2020-06-18 18:27:00\",\"endTime\":\"2020-06-18 20:27:00\",\"title\":\"non\",\"subject\":\"yeeep\",\"description\":\"lllxxx\",\"liveType\":\"push\",\"ingestSrc\":\"\",\"videoType\":\"\",\"cameraNum\": 1,\"record\": 0,\"snapshot\": 0,\"demand\": 0,\"transcode\": 0,\"timeShift\": 0,\"playMode\": 0,\"delayTime\": 0,\"cdnType\": 0}";

        try {
            System.out.println(URLDecoder.decode("%7B%22vid%22%3A%22005i4b5Sp6zXUI2PKQGY8C%22%7D"));
            System.out.println(URLDecoder.decode("%7B%22cameraNum%22%3A1%2C%22cdnType%22%3A0%2C%22delayTime%22%3A0%2C%22demand%22%3A0%2C%22description%22%3A%22lllxxx%22%2C%22endTime%22%3A%222020-06-19%2020%3A27%3A00%22%2C%22ingestSrc%22%3A%22%22%2C%22liveType%22%3A%22push%22%2C%22playMode%22%3A0%2C%22record%22%3A0%2C%22snapshot%22%3A0%2C%22startTime%22%3A%222020-06-19%2018%3A27%3A00%22%2C%22subject%22%3A%22yeeep%22%2C%22timeShift%22%3A0%2C%22title%22%3A%22non%22%2C%22transcode%22%3A0%2C%22videoType%22%3A%22%22%7D"));
            System.out.println(URLDecoder.decode("%7B%22title%22%3A%22non%22%2C%22startTime%22%3A%222020-06-19%2018%3A27%3A00%22%2C%22endTime%22%3A%222020-06-19%2020%3A27%3A00%22%2C%22subject%22%3A%22yeeep%22%2C%22description%22%3A%22lllxxx%22%2C%22liveType%22%3A%22push%22%2C%22ingestSrc%22%3A%22%22%2C%22imgUrl%22%3Anull%2C%22videoType%22%3A%22%22%2C%22cameraNum%22%3A1%2C%22record%22%3A0%2C%22snapshot%22%3A0%2C%22demand%22%3A0%2C%22transcode%22%3A0%2C%22timeShift%22%3A0%2C%22playMode%22%3A0%2C%22delayTime%22%3A0%2C%22lowDelay%22%3Anull%2C%22cdnType%22%3A0%2C%22userId%22%3Anull%7D"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成token
     * @param secretKey 密钥串
     * @param plain 公钥串
     * @return 返回生成token
     */
    private static String getToken(String secretKey, String plain) {
        byte[] keyBytes = secretKey.getBytes();
        byte[] plainBytes = plain.getBytes();
        try {
            //加密算法
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            //密钥值
            SecretKeySpec secret_key = new SecretKeySpec(keyBytes, "HmacSHA256");
            //密钥初始化
            sha256_HMAC.init(secret_key);
            byte[] hashs = sha256_HMAC.doFinal(plainBytes);
            StringBuilder sb = new StringBuilder();
            for (byte x : hashs) {
                String b = Integer.toHexString(x & 0XFF);
                if (b.length() == 1) {
                    b = '0' + b;
                }
                sb.append(b);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
