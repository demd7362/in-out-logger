package com.example.demo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum LoginType {
    KAKAO;

    private static final Map<String, LoginType> CONVERTER = new HashMap<>();

    static {
        for(LoginType loginType : values()) {
            CONVERTER.put(loginType.name(), loginType);
        }
    }

    public static LoginType nameOf(String name){
        return CONVERTER.get(name.toUpperCase());
    };

}

