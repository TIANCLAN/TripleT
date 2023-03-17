package cn.edu.scnu.echarts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "cn.edu.scnu.echarts.mapper")
public class StarterEchartsCenter {
    public static void main(String[] args) {
        SpringApplication.run(StarterEchartsCenter.class, args);
    }
        //为ribbon客户端准备的内部调用其他服务的对象
        @Bean
        @LoadBalanced
        public RestTemplate initRestTemplateProduct () {
            return new RestTemplate();
        }
    }
