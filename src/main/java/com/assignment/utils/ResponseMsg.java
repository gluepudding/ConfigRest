package com.assignment.utils;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMsg {
    int code;
    JSONObject data;

    public ResponseMsg(int code) {
        this.code = code;
    }

    public ResponseMsg(int code, JSONObject data) {
        this.code = code;
        this.data = data;
    }
}
