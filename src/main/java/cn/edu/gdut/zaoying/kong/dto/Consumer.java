package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Consumer {
    private String id;
    private String customId;
    private String username;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
