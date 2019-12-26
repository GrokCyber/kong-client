package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.List;

@Data
public class Target {
    private String id;
    private String name;
    private Upstream upstream;
    private String target;
    private Integer weight;
    private List<String> tags;
    private Float createdAt;
}
