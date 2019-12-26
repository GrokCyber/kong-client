package cn.edu.gdut.zaoying.kong.client;

import cn.edu.gdut.zaoying.kong.RestfulClient;
import cn.edu.gdut.zaoying.kong.dto.Plugin;
import cn.edu.gdut.zaoying.kong.dto.Response;
import cn.edu.gdut.zaoying.kong.dto.Route;
import cn.edu.gdut.zaoying.kong.dto.Service;
import feign.Param;
import feign.RequestLine;

public interface ServiceClient extends RestfulClient<Service> {
    String PLUGINS = GET + "/plugins";
    String ROUTES = GET + "/routes";

    @RequestLine(PLUGINS)
    Response<Plugin> plugins(@Param("id") String serviceId);

    @RequestLine(ROUTES)
    Response<Route> routes(@Param("id") String serviceId);
}
