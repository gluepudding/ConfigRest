package com.assignment.service;

import com.assignment.model.entity.ConfigData;

import java.util.List;

public interface ConfigService {

    ConfigData getConfigByAppCodeAndVersion(String appCode, String version);

    List<ConfigData> getConfigByAppCode(String appCode);

    ConfigData addOrUpdateConfig(String appCode, String version, String jsonStr, String eTag);
}
