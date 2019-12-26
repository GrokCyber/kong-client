package cn.edu.gdut.zaoying.kong;

import cn.edu.gdut.zaoying.kong.client.PluginClient;
import cn.edu.gdut.zaoying.kong.dto.Plugin;
import cn.edu.gdut.zaoying.kong.dto.Service;
import cn.edu.gdut.zaoying.kong.plugin.ACL;
import cn.edu.gdut.zaoying.kong.plugin.KeyAuth;
import cn.edu.gdut.zaoying.kong.plugin.PluginConfigException;
import cn.edu.gdut.zaoying.kong.plugin.RateLimiting;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

public class PluginHelper {

    @Resource
    private SerializeConfig config;

    @Resource
    private PluginClient pluginClient;

    /**
     * 创建key-auth插件
     * @param service Kong服务
     * @return key-auth插件
     */
    public Plugin applyKeyAuth(Service service) {

        KeyAuth keyAuth = new KeyAuth();
        keyAuth.setKeyNames(Collections.singletonList("apiKey"));
        Plugin ka = new Plugin();
        ka.setName("key-auth");
        ka.setEnabled(true);
        ka.setService(service);
        ka.setConfig(JSON.parseObject(JSON.toJSONString(keyAuth, config)));
        return KongClient.call(() -> pluginClient.create(ka)).execute();
    }

    /**
     * 创建acl插件
     * @param service Kong服务
     * @return ACL插件
     */
    public Plugin applyACL(Service service) {

        ACL acl = new ACL();
        acl.setWhitelist(Collections.singletonList(service.getName()));
        Plugin a = new Plugin();
        a.setName("acl");
        a.setEnabled(true);
        a.setService(service);
        a.setConfig(JSON.parseObject(JSON.toJSONString(acl, config)));
        return KongClient.call(() -> pluginClient.create(a)).execute();
    }

    /**
     * 应用RateLimiting插件对服务进行限流
     * @param rateLimit 限流配置，JSON格式
     * @param service Kong服务
     * @return RateLimiting插件
     */
    public Plugin applyRateLimiting(String rateLimit, Service service) {

        if (rateLimit != null && !"".equals(rateLimit)){
            Map<String,Object> config = JSON.parseObject(rateLimit);

            Plugin rateLimiting = new Plugin();
            rateLimiting.setName("rate-limiting");
            rateLimiting.setService(service);
            rateLimiting.setConfig(config);

            return KongClient.call(() -> pluginClient.create(rateLimiting)).execute();
        }
        return new Plugin();
    }

    /**
     * 限流插件RateLimiting的配置辅助类
     * @param unit 时间单位(0-秒、1-分、2-时、3-日、4-月、5-年)
     * @param limit 单位时间内限流次数
     * @param limitRuleIndex 限流规则（0-用户，1-凭据，2-请求IP）
     * @return JSON格式的限流配置
     */
    public String generateRateLimiting(Integer unit, Long limit, Integer limitRuleIndex){

        RateLimiting rateLimiting = new RateLimiting();

        //unit的范围是0~5，分别代表秒、分、时、日、月、年
        switch (unit){
            case 0:
                rateLimiting.setSecond(limit.intValue());
                break;
            case 1:
                rateLimiting.setMinute(limit.intValue());
                break;
            case 2:
                rateLimiting.setHour(limit.intValue());
                break;
            case 3:
                rateLimiting.setDay(limit);
                break;
            case 4:
                rateLimiting.setMonth(limit);
                break;
            case 5:
                rateLimiting.setYear(limit);
                break;
            default:
                throw new PluginConfigException("限流的时间单位不合法");
        }

        //校验限流规则
        if (limitRuleIndex < 0 || limitRuleIndex >= RateLimiting.LimitRule.values().length) {
            throw new PluginConfigException("限流规则不合法");
        }

        //设置限流规则（0-用户，1-凭据，2-请求IP）
        RateLimiting.LimitRule limitRule = RateLimiting.LimitRule.values()[limitRuleIndex];
        rateLimiting.setLimitBy(limitRule);

        //设置限流策略(暂时不考虑集群或者Redis的限流策略)
        rateLimiting.setPolicy(RateLimiting.Policy.LOCAL);

        return JSON.toJSONString(rateLimiting, config, SerializerFeature.WriteEnumUsingToString);
    }
}
