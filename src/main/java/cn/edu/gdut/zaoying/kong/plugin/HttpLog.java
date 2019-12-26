package cn.edu.gdut.zaoying.kong.plugin;

import lombok.Data;

@Data
public class HttpLog {
    private Integer flushTimeout;
    private String httpEndpoint;
    private Integer retryCount;
    private Long timeout;
    private Integer queue_size;
    private Integer keepalive;
    private String contentType;
    private String method;
}
