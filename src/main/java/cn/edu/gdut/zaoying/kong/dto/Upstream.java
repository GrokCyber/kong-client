package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Upstream {
    private String id;
    private String name;
    private String hashOn;
    private String hashFallback;
    private String hashOnHeader;
    private String hashFallbackHeader;
    private String hashOnCookie;
    private String hashOnCookiePath;
    private HealthChecks healthchecks;
    private Long slots;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class HealthChecks {
        private HealthCheck active;
        private HealthCheck passive;
    }

    @Data
    public static class HealthCheck {
        private String type;
        private String httpPath;
        private Boolean httpsVerifyCertificate;
        private Health healthy;
        private Health unhealthy;
        private Integer timeout;
        private SNI httpsSni;
        private Integer concurrency;
    }

    @Data
    public static class Health {
        private List<Integer> httpStatuses;
        private Integer tcpFailures;
        private Integer timeouts;
        private Integer httpFailures;
        private Integer interval;
        private Integer successes;
    }
}