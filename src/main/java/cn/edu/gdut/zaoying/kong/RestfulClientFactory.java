package cn.edu.gdut.zaoying.kong;

import cn.edu.gdut.zaoying.kong.client.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import feign.Feign;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.Data;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
public class RestfulClientFactory {

    @Resource
    private KongConfig config;

    @Resource
//    @Qualifier("customizedErrorDecoder")
    private ErrorDecoder errorDecoder;

    public <T> T getClient(Class<T> clazz, String url, ErrorDecoder errorDecoder){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

        Feign.Builder builder =  Feign.builder()
                .retryer(Retryer.NEVER_RETRY)
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .client(new ApacheHttpClient());
        if(errorDecoder == null){
            builder.errorDecoder(new ErrorDecoder.Default());
        }
        else {
            builder.errorDecoder(errorDecoder);
        }
        if (config.getLog()) {
            builder.requestInterceptor(System.out::println);
        }
        return builder.target(clazz, url);
    }

    public <T> T getClient(Class<T> clazz, String url) {
        return getClient(clazz, url, errorDecoder);
    }

//    @Bean
    public ServiceClient serviceClient(){
        String url = config.getAdmin() + "/services";
        return getClient(ServiceClient.class, url);
    }

//    @Bean
    public PluginClient pluginClient(){
        String url = config.getAdmin() + "/plugins";
        return getClient(PluginClient.class, url);
    }

//    @Bean
    public ConsumerClient consumerClient(){
        String url = config.getAdmin() + "/consumers";
        return getClient(ConsumerClient.class, url);
    }

//    @Bean
    public RouteClient routeClient(){
        String url = config.getAdmin() + "/routes";
        return getClient(RouteClient.class, url);
    }

//    @Bean
    public UpstreamClient upstreamClient(){
        String url = config.getAdmin() + "/upstreams";
        return getClient(UpstreamClient.class, url);
    }

//    @Bean
    public TargetClient targetClient(){
        String url = config.getAdmin();
        return getClient(TargetClient.class, url);
    }

//    @Bean
    public SNIClient sniClient(){
        String url = config.getAdmin() + "/snis";
        return getClient(SNIClient.class, url);
    }

//    @Bean
    public CertificateClient certificateClient(){
        String url = config.getAdmin() + "/certificates";
        return getClient(CertificateClient.class, url);
    }
}
