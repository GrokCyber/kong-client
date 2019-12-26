package cn.edu.gdut.zaoying.kong.plugin;

import lombok.Data;

import java.util.List;

@Data
public class ACL {
    private List<String> whitelist;
    private List<String> blacklist;
    private Boolean hideGroupsHeader;
}
