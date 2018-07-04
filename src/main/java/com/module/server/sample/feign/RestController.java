package com.module.server.sample.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * TODO description：使用RestTemplate调用
 *
 * @author bing.li
 * @version v1.0
 * @date 2018-07-04 19:25
 */
@RequestMapping("/rest")
@SuppressWarnings("all")
public class RestController {


    @Autowired
    @Qualifier("restTemplate1")
    private RestTemplate restTemplate1;

    @Autowired
    @Qualifier("restTemplate2")
    private RestTemplate restTemplate2;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * Gets user count。这种方式是事先知道要调用服务的地址。
     *
     * @return the user count
     */
    @GetMapping("getUserCount1")
    public String getUserCount1() {
        try {
            Long count = restTemplate1.getForObject( "http://localhost:9010/user/findCount", long.class );
            System.out.println( count );
            return "succee,cound=" + count;
        } catch (Exception e) {
            e.printStackTrace();
            return "error{ }" + e.getMessage();
        }
    }

    /**
     * Gets user count 2.带参数的
     * 注意返回类型
     *
     * @return the user count 2
     */
    @GetMapping("findByName")
    public String findByName() {
        try {
            String name = "张三";
            UriComponents uriComponents = UriComponentsBuilder.fromUriString( "http://localhost:9010/user/findByName?name={name}" )
                    .build().expand( name ).encode();
            URI uri = uriComponents.toUri();
            //得到返回信息
            ResponseEntity <String> entity = restTemplate1.getForEntity( uri, String.class );
            String body = entity.getBody();
            System.out.println( body );
            return "succee:" + body;
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

    /**
     * Gets user count 3.
     * 使用RestTemplate,通过使用 LoadBalancerClient 远程调用时，服务方需要事先在eureka中注入。
     * REDIS 为eureka 注册的服务名
     *
     * @return the user count 3
     */
    @GetMapping("getUserCount3")
    public String getUserCount3() {
        try {
            //调用eureka 注册的服务名
            ServiceInstance serviceInstance = loadBalancerClient.choose( "client-demo" );
            String url = String.format( "http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/user/findCount" );
            Long aLong = restTemplate1.getForObject( url, long.class );
            System.out.println( aLong );
            return "succee:" + aLong;
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

    /**
     * Gets user count 4.用restTemplate2 @LoadBalanced 来注解这样可以达到负载均衡，若没有该注解可能是缺少ribbon依赖。
     * TODO 推荐使用
     *
     * @return the user count 4
     */
    @GetMapping("getUserCount4")
    public String getUserCount4() {
        try {
            //调用eureka 注册的服务名
            Long aLong = restTemplate2.getForObject( "http://client-demo/redis/test", long.class );
            System.out.println( aLong );
            return "succee:" + aLong;
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

}
