package com.zc.springbootbase.controller;

import com.zc.springbootbase.bean.Programmer;
import com.zc.springbootbase.bean.ProgrammerPlus;
import com.zc.springbootbase.model.Access;
import com.zc.springbootbase.service.TestService;
import com.zc.springbootbase.thread.ThreadPoolSingleton;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@EnableAutoConfiguration
@RestController
@CrossOrigin // So that javascript can be hosted elsewhere
public class FrontendController {
    public static final Logger logger = LoggerFactory.getLogger(FrontendController.class);
    final RestTemplate restTemplate;
    @Autowired
    TestService testServiceImpl;

    @Autowired
    FrontendController(RestTemplateBuilder restTemplateBuilder) {
        //生成一个设置了连接超时时间、请求超时时间、异常最大重试次数的httpClient
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(30000).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config).setRetryHandler(new DefaultHttpRequestRetryHandler(5, false));
        HttpClient httpClient = builder.build();
        //使用httpClient创建一个ClientHttpRequestFactory的实现
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //ClientHttpRequestFactory作为参数构造一个使用作为底层的RestTemplate
        this.restTemplate = new RestTemplate(requestFactory);
    }

    @RequestMapping("/")
    public String callBackend() {
        String str = "";
        try {
            str = restTemplate.getForObject("http://localhost:8088/api2", String.class);
        } catch (HttpServerErrorException e) {
            return str + " :ERROR return";
        }
        return str;
    }

    @RequestMapping("/2")
    public String callBackend2() {
        ResponseEntity<ProgrammerPlus[]> programmerEntity = restTemplate.getForEntity("http://localhost:8088/restful/programmers", ProgrammerPlus[].class);

        List<ProgrammerPlus> list = Arrays.asList(programmerEntity.getBody());
        for (ProgrammerPlus p : list) {
            System.out.println(p.getName());
        }
        System.out.println(programmerEntity.getStatusCode());
        System.out.println(programmerEntity.getStatusCodeValue());
        System.out.println(programmerEntity.getBody());
        System.out.println(programmerEntity.getHeaders());
        System.out.println(programmerEntity.getClass());
        return null;
    }


    @RequestMapping("/3")
    public String callBackend3() {
        ResponseEntity<Programmer> programmerEntity = restTemplate.getForEntity("http://localhost:8088/restful/test", Programmer.class);

        System.out.println(programmerEntity.getBody().getName());

        System.out.println(programmerEntity.getStatusCode());
        System.out.println(programmerEntity.getStatusCodeValue());
        System.out.println(programmerEntity.getBody());
        System.out.println(programmerEntity.getHeaders());
        System.out.println(programmerEntity.getClass());
        return null;
    }


    @RequestMapping("/4")
    public String callBackend4() {
        ResponseEntity<String> str = null;
        try {
            str = restTemplate.getForEntity("http://localhost:8088/api2", String.class);
        } catch (Exception e) {
            logger.info("ENTRY ERROR");
        }
        logger.info("this is {}", String.valueOf(str.getStatusCodeValue()));
        return str.getBody();
    }

    /**
     * @Description :
     * @Param: [str, b]
     * @Return : java.lang.String
     * @Author: zc
     * @Date : 2020/3/10 23:40
     */

    public String test(String str, int b) {
        System.out.println();
        return null;
    }

    /**
     * @Description :
     * @Author: zc
     * @Date : 2020/4/13 11:06
     */
    @RequestMapping("/asyncCall")
    public String call(Programmer programmer) {
        logger.info("i come to test ");
        logger.info("hello");
        return null;
    }

    /**
     * @Description :
     * @Author: zc
     * @Date : 2020/4/9 17:35
     */
    @RequestMapping("/readDb")
    public String readDb() {
        Access access = new Access();
        access.setUsername("12345678");
        int i = testServiceImpl.insert(access);
        return "SUCCESS";
    }

    /**
     * @Description :
     * @Author: zc
     * @Date : 2020/4/23 10:11
     */
    @RequestMapping("/asyncCall2")
    public String asynccall() {
        logger.info("start submit");
        testServiceImpl.asyncCall();
        logger.info("end submit");
        return "success";
    }

    @RequestMapping("/asyncCall3")
    public String asyncCall3(){
        logger.info("start submit");
        ThreadPoolSingleton.getInstance().executeTask(
                ()->{
                    logger.info("start my task");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("end my task");
                }
        );
        logger.info("end submit");
        return "success";
    }

}
