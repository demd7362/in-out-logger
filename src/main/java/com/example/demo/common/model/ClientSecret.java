package com.example.demo.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClientSecret {
    private String clientSecret;
    private String clientId;
}
