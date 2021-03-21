package com.assignment.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigResp {

    private String version;

    private String jsonConf;

    private String creationTime;
}
