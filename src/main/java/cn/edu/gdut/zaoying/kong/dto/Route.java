package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Route {
    private String id;
    private String name;
    private Boolean stripPath;
    private Boolean preserveHost;
    private Integer regexPriority;
    private List<String> hosts;
    private List<String> paths;
    private List<String> methods;
    private List<String> protocols;
    private List<String> destinations;
    private List<String> sources;
    private Service service;
    private List<SNI> snis;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
