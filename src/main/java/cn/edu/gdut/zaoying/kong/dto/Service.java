package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Service {
    private String id;
    private String name;
    private String protocol;
    private String host;
    private Integer port;
    private String path;
    private String projectId;
    private Integer retries;
    private Long readTimeout;
    private Long writeTimeout;
    private Long connectTimeout;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
