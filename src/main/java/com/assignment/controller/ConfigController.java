package com.assignment.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.assignment.common.annotation.Auth;
import com.assignment.model.entity.ConfigData;
import com.assignment.common.bean.AuthEnum;
import com.assignment.common.bean.ResponseMsg;
import com.assignment.common.bean.StatusEnum;
import com.assignment.service.ConfigService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.assignment.common.utils.ConfigRestUtil.buildJsonArray;
import static com.assignment.common.utils.ConfigRestUtil.convertToConfigResp;

@Slf4j
@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Auth(role = AuthEnum.ADMIN)
    @RequestMapping(value = "/{appCode}/config/{version}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMsg> getConfigByAppCodeAndVersion(@PathVariable("appCode") String appCode,
                                                                    @PathVariable("version") String version) throws JsonProcessingException {
        log.info("Displaying config for AppCode: {} with Version: {}", appCode, version);

        ConfigData configData = configService.getConfigByAppCodeAndVersion(appCode, version);

        ResponseMsg responseMsg = new ResponseMsg(StatusEnum.SUCCESS.getState(), (JSONObject) JSONObject.toJSON(convertToConfigResp(configData)));

        HttpHeaders headers = new HttpHeaders();
        headers.add("If-Match", configData.getHashCode());

        return new ResponseEntity<>(responseMsg, headers, HttpStatus.OK);

    }

    @Auth(role = AuthEnum.ADMIN)
    @RequestMapping(value = "/{appCode}/config", method = RequestMethod.GET)
    public ResponseEntity<ResponseMsg> getConfigByAppCode(@PathVariable("appCode") String appCode) {

        log.info("Displaying config list for AppCode: {}", appCode);

        List<ConfigData> configDataList = configService.getConfigByAppCode(appCode);

        JSONArray jsonArray = new JSONArray();
        configDataList.forEach(configData -> {
            jsonArray.add(convertToConfigResp(configData));
        });

        ResponseMsg responseMsg = new ResponseMsg(StatusEnum.SUCCESS.getState(), (JSONObject) JSONObject.toJSON(buildJsonArray("configs", jsonArray)));
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);

    }

    @Auth(role = AuthEnum.ADMIN)
    @RequestMapping(value = "/{appCode}/config/{version}", method = RequestMethod.POST)
    public ResponseEntity<ResponseMsg> addOrUpdateConfig(@PathVariable("appCode") String appCode,
                                                  @PathVariable("version") String version,
                                                  @RequestBody JSONObject jsonParam,
                                                  @RequestHeader(value = "If-Match", required = false) String eTag) {

        log.info("Adding/Updating config: " + jsonParam.toJSONString());

        ConfigData configData = configService.addOrUpdateConfig(appCode, version, jsonParam.toJSONString(), eTag);

        ResponseMsg responseMsg = new ResponseMsg(StatusEnum.SUCCESS.getState(), (JSONObject) JSONObject.toJSON(convertToConfigResp(configData)));

        HttpHeaders headers = new HttpHeaders();
        headers.add("If-Match", configData.getHashCode());

        return new ResponseEntity<>(responseMsg, headers, HttpStatus.OK);
    }

}
