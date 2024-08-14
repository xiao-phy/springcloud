package com.atguigu.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component //不要忘记
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered
{

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";// 开始调用的方法

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        // 1. 先记录访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME,System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            Long beginVistiTime =   exchange.getAttribute(BEGIN_VISIT_TIME);
            if(beginVistiTime != null){
                log.info("访问接口主机:" + exchange.getRequest().getURI().getHost());
                log.info("访问接口端口:" + exchange.getRequest().getURI().getPort());
                log.info("访问接口URL:" + exchange.getRequest().getURI().getPath());
                log.info("访问接口URL后面参数:" + exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口的时长:" + (System.currentTimeMillis() - beginVistiTime ) + "毫秒");
                log.info("==============分割线==============");
                System.out.println();
            }
        }));
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}