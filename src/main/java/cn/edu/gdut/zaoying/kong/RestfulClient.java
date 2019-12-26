package cn.edu.gdut.zaoying.kong;

import cn.edu.gdut.zaoying.kong.dto.Response;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RestfulClient<T> {

    String CREATE = "POST ";
    String LIST = "GET ";
    String GET = "GET /{id}";
    String UPDATE = "PUT /{id}";
    String PATCH = "PATCH /{id}";
    String DELETE = "DELETE /{id}";

    @RequestLine(GET)
    T get(@Param("id") String id);

    @RequestLine(LIST)
    Response<T> list();

    @RequestLine(CREATE)
    @Headers("Content-Type: application/json")
    T create(T T);

    @RequestLine(UPDATE)
    @Headers("Content-Type: application/json")
    T update(@Param("id") String id, T entity);

    @RequestLine(PATCH)
    @Headers("Content-Type: application/json")
    T patch(@Param("id") String id, T entity);

    @RequestLine(DELETE)
    void delete(@Param("id") String id);
}
