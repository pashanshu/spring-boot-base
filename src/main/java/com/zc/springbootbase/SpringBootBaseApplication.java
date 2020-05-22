package com.zc.springbootbase;

import com.zc.springbootbase.netty.EchoServer;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.zc.springbootbase.dao")
@ServletComponentScan
public class SpringBootBaseApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Autowired
    private EchoServer echoServer;

    /**
     * @Description :*@SpringBootApplication 注解是一个复合注解,里面包含了@ComponentScan注解默认开启包扫描，扫描与主程序所在包及其子包，对于本工程而言 默认扫描 com.zc.springbootbase
     * @Author: zc
     * @Date : 2020/4/13 11:07
    */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = echoServer.start(url,port);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                echoServer.destroy();
            }
        });
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        future.channel().closeFuture().syncUninterruptibly();
    }
}

