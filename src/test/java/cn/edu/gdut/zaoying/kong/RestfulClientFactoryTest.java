package cn.edu.gdut.zaoying.kong;

import org.junit.Test;

import cn.edu.gdut.zaoying.kong.client.CertificateClient;
import cn.edu.gdut.zaoying.kong.client.ConsumerClient;
import cn.edu.gdut.zaoying.kong.client.PluginClient;
import cn.edu.gdut.zaoying.kong.client.RouteClient;
import cn.edu.gdut.zaoying.kong.client.SNIClient;
import cn.edu.gdut.zaoying.kong.client.ServiceClient;
import cn.edu.gdut.zaoying.kong.client.TargetClient;
import cn.edu.gdut.zaoying.kong.client.UpstreamClient;

public class RestfulClientFactoryTest {
    RestfulClientFactory factory = new RestfulClientFactory();

    @Test()
    public void getClient() {
        KongConfig config = new KongConfig();
        factory.setConfig(config);

        ServiceClient serviceClient = factory.serviceClient();
        assert serviceClient != null : "不应该为空";
        
        PluginClient pluginClient = factory.pluginClient();
        assert pluginClient != null : "不应该为空";
        
        ConsumerClient consumerClient = factory.consumerClient();
        assert consumerClient != null : "不应该为空";
        
        RouteClient routeClient = factory.routeClient();
        assert routeClient != null : "不应该为空";
        
        UpstreamClient upstreamClient = factory.upstreamClient();
        assert upstreamClient != null : "不应该为空";
        
        TargetClient targetClient = factory.targetClient();
        assert targetClient != null : "不应该为空";
        
        SNIClient sniClient = factory.sniClient();
        assert sniClient != null : "不应该为空";
        
        CertificateClient certificateClient = factory.certificateClient();
        assert certificateClient != null : "不应该为空";
    }
}
