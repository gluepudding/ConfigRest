package com.assignment.common.bean;

public enum StatusEnum {
    SUCCESS(200, "Success"),
    INVALID(400, "Invalid request"),
    AUTHEN_FAIL(401, "Authentication failure"),
    AUTHOR_FAIL(403, "Authorization failure"),
    NOT_FOUND(404, "Not found"),
    INTERAL_ERR(500, "Internal error");

    private final int state;
    private final String stateInfo;

    StatusEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StatusEnum stateOf (int index){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getState() == index)
                return statusEnum;
        }
        return null;
    }
}
