package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Certificate {
    private String id;
    private String cert;
    private String key;
    private List<String> snis;
    private List<String> tags;
    private Date createdAt;
}
