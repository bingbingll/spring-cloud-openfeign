package com.module.server.sample.feign.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description：使用RestTemplate服务调用
 *
 * @author bing.li
 * @version v1.0
 * @date 2018/7/4 19:18
 */
@Configuration
@SuppressWarnings("all")
public class RestTemplateConfig {

    /**
     * TODO 一个主要的RestTemplate类
     *
     * @return rest template
     */
    @Primary
    @Bean(name = "restTemplate1")
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }
    /**
     * TODO 一个RestTemplate类 不过这个添加了@LoadBalanced 进行负载均衡。
     *
     * @return the rest template
     * @date 2018 /6/27 13:37
     */
    @Bean("restTemplate2")
    @LoadBalanced
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }


    /**
     * TODO 可以进行负载均衡，该服务是从eureka中拿到实例。
     *
     * @return the redis server
     */
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//    @Bean
//    public RedisServer redisServer() {
//        ServiceInstance serviceInstance = loadBalancerClient.choose( "redis" );
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//
//        String url = String.format( "http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() );
//        log.info( "RedisServer 调用地址{ }", url );
//        return new RedisServer( host, port, url );
//    }




}
