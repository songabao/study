package com.microservice.fallback;


import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonHttpRequest;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;
import org.springframework.cloud.netflix.ribbon.okhttp.OkHttpRibbonResponse;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.AbstractClientHttpResponse;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import sun.nio.cs.UTF_32;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author songabao
 * @date 2019/8/5 10:30
 */
@Component
public class MyZuulFallback implements FallbackProvider{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyZuulFallback.class);
    @Override
    public String getRoute() {
        return "*";
    }
    /**
     *
     * @param route 请求的接口
     * @param cause 错误原因
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        LOGGER.error("route: "+route);
        if (cause instanceof HystrixTimeoutException) {
            //错误原因
            LOGGER.error("cause", cause);
            return new ClientHttpResponse() {
                @Override
                public HttpStatus getStatusCode() throws IOException {
                    return HttpStatus.BAD_REQUEST;
                }

                @Override
                public int getRawStatusCode() throws IOException {
                    return 400;
                }

                @Override
                public String getStatusText() throws IOException {
                    return "400";
                }

                @Override
                public void close() {

                }

                @Override
                public InputStream getBody() throws IOException {
                    return new ByteInputStream("请求失败,请稍后重试".getBytes(), 1024);
                }

                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    MediaType mediaType = new MediaType("Application", "json", Charset.forName("UTF-8"));
                    httpHeaders.setContentType(mediaType);
                    return httpHeaders;
                }
            };
        }else{
            return  null;
        }
    }
}
