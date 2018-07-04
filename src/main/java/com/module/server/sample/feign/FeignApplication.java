package com.module.server.sample.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description：使用feign 和 RestTemplate 进行远程调用。
 *
 * @author bing.li
 * @version v1.0
 * @date 2018/7/4 18:52
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignApplication {

	public static void main(String[] args) {

		SpringApplication.run(FeignApplication.class, args);
	}
}
