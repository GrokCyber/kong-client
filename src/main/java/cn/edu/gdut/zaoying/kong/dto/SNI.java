package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SNI {
    private String id;
    private String name;
    private Certificate certificate;
    private List<String> tags;
    private Date createdAt;
}
