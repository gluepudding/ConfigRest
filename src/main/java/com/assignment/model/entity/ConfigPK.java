package com.assignment.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConfigPK implements Serializable {

    private static final long serialVersionUID = 7946694510501666366L;

    private String appCode;

    private String version;
}
