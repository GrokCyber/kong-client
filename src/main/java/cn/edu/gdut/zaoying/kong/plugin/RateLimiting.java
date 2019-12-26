package cn.edu.gdut.zaoying.kong.plugin;

import lombok.Data;

@Data
public class RateLimiting {
    private Integer second;
    private Integer minute;
    private Integer hour;
    private Long day;
    private Long month;
    private Long year;
    private LimitRule limitBy;
    private Policy policy;
    private Boolean faultTolerant;
    private Boolean hideClientHeader;
    private String redisHost;
    private Integer redisPort;
    private String redisPassword;
    private Long redisTimeout;
    private String redisDatabase;

    /**
     * 限流规则
     */
    public enum LimitRule {
        /**
         * 用户
         */
        CONSUMER,
        /**
         * 用户凭据
         */
        CREDENTIAL,
        /**
         * 请求IP
         */
        IP;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    /**
     * 限流次数统计的持久化策略
     */
    public enum Policy {
        /**
         * 本地内存
         */
        LOCAL,
        /**
         * 集群内存
         */
        CLUSTER,
        /**
         * Redis缓存
         */
        REDIS;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
