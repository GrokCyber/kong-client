package cn.edu.gdut.zaoying.kong.plugin;

import lombok.Data;

import java.util.List;

@Data
public class KeyAuth {
    private Boolean runOnPreflight;
    private Boolean hideCredentials;
    private Boolean keyInBody;
    private String anonymous;
    private List<String> keyNames;
}
