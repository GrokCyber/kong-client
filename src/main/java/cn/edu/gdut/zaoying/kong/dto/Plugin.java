package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Plugin {
    private String id;
    private String name;
    private Boolean enabled;
    private Map<String,Object> config;
    private Service service;
    private String runOn;
    private Consumer consumer;
    private Route route;
    private List<String> protocols;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
