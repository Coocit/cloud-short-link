package com.coocit.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory){
        return new RestTemplate(requestFactory);
    }

    // 非池化
    /*@Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(10000);
        return factory;
    }*/


    /**
     * 池化
     * http请求工厂
     *
     * @return {@link ClientHttpRequestFactory}
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory(){
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient(){


        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        // http 连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);


        //设置连接池最大是500个连接
        connectionManager.setMaxTotal(500);
        //MaxPerRoute是对maxTotal的细分，每个主机的并发最大是300，route是指域名
        connectionManager.setDefaultMaxPerRoute(300);
        /*
         * 只请求 coocit.com,最大并发300
         *
         * 请求 coocit.com,最大并发300
         * 请求 open1024.com,最大并发200
         *
         * // MaxTotal=400 DefaultMaxPerRoute=200
         * // 只连接到http://coocit.net时，到这个主机的并发最多只有200；而不是400；
         * // 而连接到http://coocit.net 和 http://open1024.com时，到每个主机的并发最多只有200；
         * // 即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute。
         **/

        RequestConfig requestConfig = RequestConfig.custom()
                //返回数据的超时时间
                .setSocketTimeout(20000)
                //连接上服务器的超时时间
                .setConnectTimeout(10000)
                //从连接池中获取连接的超时时间
                .setConnectionRequestTimeout(1000)
                .build();


        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();

        return closeableHttpClient;
    }

}
