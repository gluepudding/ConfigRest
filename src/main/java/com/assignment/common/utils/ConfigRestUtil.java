package com.assignment.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.assignment.model.dto.ConfigResp;
import com.assignment.model.entity.ConfigData;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;

public final class ConfigRestUtil {

    public static ConfigResp convertToConfigResp(ConfigData configData) {

        ConfigResp configResp = new ConfigResp();

        if (configData != null) {
            configResp.setVersion(configData.getVersion());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            configResp.setCreationTime(sdf.format(configData.getCreationTime()));
            configResp.setJsonConf(configData.getJsonConf());
        }

        return configResp;
    }

    public static JSONObject buildJsonArray (String key, JSONArray jsonArray){

        JSONObject jsonObject = new JSONObject();

        if (StringUtils.hasText(key)){
            jsonObject.put(key, jsonArray);
        }

        return jsonObject;
    }
}
