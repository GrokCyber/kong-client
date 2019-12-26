package cn.edu.gdut.zaoying.kong.client;

import cn.edu.gdut.zaoying.kong.dto.Response;
import cn.edu.gdut.zaoying.kong.dto.Target;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface TargetClient{

    String PARENT = "/upstreams/{upstreamId}/targets";

    String CREATE = "POST " + PARENT;
    String LIST = "GET " + PARENT;
    String UPDATE = CREATE + "/{id}";
    String DELETE = "DELETE " + PARENT + "/{id}";

    String ALL = LIST + "/all";
    String HEALTHY = UPDATE + "/healthy";
    String UNHEALTHY = UPDATE + "/unhealthy";

    @RequestLine(LIST)
    Response<Target> list(@Param("upstreamId") String upstreamId);

    @RequestLine(CREATE)
    @Headers("Content-Type: application/json")
    Target create(@Param("upstreamId") String upstreamId, Target target);

    @RequestLine(DELETE)
    void delete(@Param("upstreamId") String upstreamId, @Param("id") String id);

    @RequestLine(ALL)
    Response<Target> all(@Param("upstreamId") String upstreamId);

    @RequestLine(HEALTHY)
    void healthy(@Param("upstreamId") String upstreamId, @Param("id") String id);

    @RequestLine(UNHEALTHY)
    void unhealthy(@Param("upstreamId") String upstreamId, @Param("id") String id);
}
