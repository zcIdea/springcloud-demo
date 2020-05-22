package com.chuan.demo.api;

import com.chuan.demo.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

//FallbackFactory的优点是可以获取到异常信息
@Component
public class TestClien3tFallbackFactory implements FallbackFactory<TestClient3> {

    @Override
    public TestClient3 create(Throwable cause) {
        return new TestClient3() {
            @Override
            public String get(String id) {
                return "get trigger hystrix open! reason:"+cause.getMessage();
            }

            @Override
            public User getUser(String id) {
                User errorUser = new User();
                errorUser.setId(123L);
                errorUser.setName("getUser.errorName");
                return errorUser;
            }
        };
    }
    
}