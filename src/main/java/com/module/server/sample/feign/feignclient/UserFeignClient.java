package com.module.server.sample.feign.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * TODO description：使用feign客户端调用 spring-cloud-config-client-sample 项目中的 /user/findCound 接口。注意修饰符是interface
 *
 * @author bing.li
 * @version v1.0
 * @date 2018-07-04 18:55
 */
@FeignClient(name = "client-demo")
@Component
public interface UserFeignClient {
    /**
     * 获取用户的总数量。需要注意点 1：服务提供方的请求类型必须一致。2：参数要一致。
     *
     * @return long
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/findCount")
    long getCount();
}
