package com.assignment.service.impl;

import com.assignment.common.exception.InvalidRequestException;
import com.assignment.common.exception.NoConfException;
import com.assignment.common.exception.NoDataException;
import com.assignment.model.entity.ConfigData;
import com.assignment.repository.ConfigDataRepository;
import com.assignment.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDataRepository configDataRepository;

    @Override
    public ConfigData getConfigByAppCodeAndVersion(String appCode, String version) throws NoConfException {

        ConfigData configData = configDataRepository.findConfigByAppCodeAndVersion(appCode, version);

        if (configData == null) {
            log.error("Could not find config for AppCode: {} with Version: {}", appCode, version);
            throw new NoConfException(appCode, version);
        }

        log.info("Fetching config for AppCode: {} with Version: {}", appCode, version);
        return configData;
    }

    public List<ConfigData> getConfigByAppCode(String appCode) throws NoDataException{

        List<ConfigData> configDataList = configDataRepository.findConfigByAppCode(appCode);

        if (configDataList.isEmpty()) {
            log.error("Could not find config list for AppCode: {}", appCode);
            throw new NoDataException(appCode);
        }

        log.info("Fetching config list for AppCode: {}", appCode);
        return configDataList;
    }

    @Override
    @Transactional
    public ConfigData addOrUpdateConfig(String appCode, String version, String jsonStr, String eTag) throws InvalidRequestException {

        checkData(jsonStr);

        String currHash = DigestUtils.md5Hex(jsonStr);

        ConfigData existConf = configDataRepository.findConfigByAppCodeAndVersion(appCode, version);

        if (existConf == null) {
            log.info("Adding config: " + jsonStr);
            ConfigData configData = buildConfigData(appCode, version, jsonStr, currHash);
            return configDataRepository.save(configData);
        } else if (eTag == null) {
            log.error("Invalid Request: updating config without Request header: If-Match");
            throw new InvalidRequestException();
        }

        if (eTag.equals(currHash)) {
            log.error("Invalid Request: Updating with same content");
            throw new InvalidRequestException();
        }

        String dbHash = existConf.getHashCode();
        if (!eTag.equals(dbHash)) {
            log.error("Invalid Request: collision with other updating");
            throw new InvalidRequestException();
        } else {
            log.info("Updating config: " + jsonStr);
            configDataRepository.updateConfigByAppCodeAndVersion(appCode, version, jsonStr, new Date(), currHash);
            return configDataRepository.findConfigByAppCodeAndVersion(appCode, version);
        }
    }

    private void checkData(String jsonStr) {
        //May do some check and sanitize
    }

    private ConfigData buildConfigData(String appCode, String version, String jsonStr, String hashCode) {

        ConfigData configData = new ConfigData();
        configData.setAppCode(appCode);
        configData.setVersion(version);
        configData.setJsonConf(jsonStr);
        configData.setCreationTime(new Date());
        configData.setHashCode(hashCode);
        return configData;
    }
}
