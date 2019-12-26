package cn.edu.gdut.zaoying.kong;

import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
//@Configuration
//@ConfigurationProperties(prefix = "kong")
public class KongConfig {
    private String admin = "http://localhost:8001";
    private String external = "http://42.159.103.88:8000";
    private Set<String> blockedHost = new HashSet<>(Arrays.asList("localhost", "127.0.0.1"));
    private Set<Long> blockedPort = new HashSet<>();
    private Set<String> blockedUrl = new HashSet<>(Arrays.asList(admin, external));;
    private Boolean log = true;
}
