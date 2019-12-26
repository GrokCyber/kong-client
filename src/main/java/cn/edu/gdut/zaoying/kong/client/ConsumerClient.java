package cn.edu.gdut.zaoying.kong.client;

import cn.edu.gdut.zaoying.kong.dto.Response;
import cn.edu.gdut.zaoying.kong.RestfulClient;
import cn.edu.gdut.zaoying.kong.dto.Consumer;
import cn.edu.gdut.zaoying.kong.plugin.KeyAuth;
import com.fasterxml.jackson.annotation.JsonProperty;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import lombok.Data;

import java.util.Map;

public interface ConsumerClient extends RestfulClient<Consumer> {

    String CREATE_KEY_AUTH = CREATE + "/{id}/key-auth";
    String CREATE_ACL = CREATE + "/{id}/acls";
    String DELETE_KEY_AUTH = DELETE + "/key-auth/{key}";
    String DELETE_ACL = DELETE + "/acls/{group}";
    String LIST_KEY_AUTH = LIST + "/{id}/key-auth?size={size}";
    String LIST_ACL = LIST + "/{id}/acls?size={size}";

    /**
     * 为消费者创建key-auth插件的key
     * @param username 用户名名称，必填
     * @param key 为用户分配的key，格式：{"key": "123456"}
     */
    @RequestLine(CREATE_KEY_AUTH)
    @Headers("Content-Type: application/json")
    Map<String,Object> applyKeyAuth(@Param("id") String username, @QueryMap Map<String, Object> key);

    /**
     * 为用户分配ACL插件的group
     * @param username 用户名名称，必填
     * @param group 为用户分配的组，格式：{"group": "group1"}
     */
    @RequestLine(CREATE_ACL)
    @Headers("Content-Type: application/json")
    Map<String,Object> applyACL(@Param("id") String username, @QueryMap Map<String, Object> group);

    /**
     * 删除指定消费者key-auth插件的key
     * @param username 用户名名称，必填
     */
    @RequestLine(DELETE_KEY_AUTH)
    Map<String,Object> deleteKeyAuth(@Param("id") String username, @Param("key") String key);

    /**
     * 将指定消费者从ACL插件的whitelist移除
     * @param username 用户名名称，必填
     */
    @RequestLine(DELETE_ACL)
    Map<String,Object> deleteACL(@Param("id") String username, @Param("group") String group);

    /**
     * 根据用户名称查询key-auth插件
     * @param username 用户名名称，必填
     */
    @RequestLine(LIST_KEY_AUTH)
    Response<KeyAuth> listKeyAuth(@Param("id") String username, @Param(value = "size", expander = QueryStringExpander.class) int size);

    /**
     * 根据用户名称查询ACL插件
     * @param username 用户名名称，必填
     */
    @RequestLine(LIST_ACL)
    Response<ACLConsumer> listACL(@Param("id") String username, @Param(value = "size", expander = QueryStringExpander.class) int size);

    @Data
    class ACLConsumer {
        private String id;
        private String group;
        @JsonProperty("created_at")
        private Long createAt;
        private Consumer consumer;
    }

    class QueryStringExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return String.valueOf(value);
        }
    }
}
