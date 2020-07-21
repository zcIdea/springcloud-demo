package com.chuan.demo.config;

import com.chuan.demo.utils.CheckSumBuilder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Random;

/**
 * feign拦截器，请求头header的设置
 * @author zhangChuan
 * @date 2020-07-21
 */
@Configuration
@Slf4j
public class HeaderPenetratedInterceptor implements RequestInterceptor {

    @Value("${test-app.token}")
    private String token;
    @Value("${test-app.appKey}")
    private String appKey;
    @Value("${test-app.appSecret}")
    private String appSecret;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames!=null){
            while (headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                log.info("==============name:【"+name+"】;"+"value:【"+value+"】");
                requestTemplate.header(name,value);
            }
        }
        requestTemplate.header("token",token);
        setheader(requestTemplate);
    }


    public void setheader(RequestTemplate requestTemplate) {

        String nonce = this.randomCode();
        String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
        // SHA1(AppSecret + Nonce + CurTime)，三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);

        // 设置请求的header
        requestTemplate.header("AppKey", appKey);
        requestTemplate.header("Nonce", nonce);
        requestTemplate.header("CurTime", curTime);
        requestTemplate.header("CheckSum", checkSum);
        requestTemplate.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    }

    public String randomCode() {
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            str.append((r.nextInt(10)));
        }
        return str.toString();
    }
}
