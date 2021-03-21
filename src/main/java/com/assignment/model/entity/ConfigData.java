package com.assignment.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@IdClass(ConfigPK.class)
@Table(name = "config_json")
public class ConfigData implements Serializable {

    private static final long serialVersionUID = -2135254243469684805L;

    @Id
    @Column(name = "app_code")
    private String appCode;

    @Id
    @Column(name = "version")
    private String version;

    @Column(name = "hash_code")
    private String hashCode;

    @Column(name = "json_conf")
    private String jsonConf;

    @Column(name = "creation_time")
    private Date creationTime;

    public ConfigData() {}

    public ConfigData(String appCode, String version, String hashCode, String jsonConf) {
        this.appCode = appCode;
        this.version = version;
        this.hashCode = hashCode;
        this.jsonConf = jsonConf;
    }
}
