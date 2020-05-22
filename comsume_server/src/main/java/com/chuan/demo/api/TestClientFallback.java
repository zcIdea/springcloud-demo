package com.chuan.demo.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestClientFallback
 * @Description: TODO
 * @Author: wanda
 * @Date 2020/5/21 17:31
 **/
@Component
public class TestClientFallback implements TestClient {
    @Override
    public List<String> getUserList(Long id, String name) {

        List<String> responseInfo=new ArrayList<String>();
        responseInfo.add("调用失败id:"+id);
        responseInfo.add("调用失败name:"+name);
        return responseInfo;
    }
}
