package com.module.server.sample.feign;

import com.module.server.sample.feign.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description：业务处理。
 *
 * @author bing.li
 * @version v1.0
 * @date 2018-07-04 19:08
 */
@RestController
@RequestMapping("/feign")
@SuppressWarnings("all")
public class FeignController {

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 调用feign接口类
     *
     * @return the string
     */
    @GetMapping("getUserCount")
    public String getUserCount() {
        try {
            long count = userFeignClient.getCount();
            return "succee,count=" + count;
        } catch (Exception e) {
            e.printStackTrace();
            return "error{ }" + e.getMessage();
        }
    }




}
