package cn.edu.gdut.zaoying.kong.dto;

import lombok.Data;

import java.util.List;

@Data
public class Response<T> {
    private String next;
    private List<T> data;
}
