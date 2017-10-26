package com.example.chenhuayu.test.js.entity;

/**
 * Created by mahaifeng on 16/4/13.
 */
public class AuthEntity {
    public String appId;
    public String token;
    public long expiration;

    public AuthEntity(String appId, String token, long expiration) {
        this.appId = appId;
        this.token = token;
        this.expiration = expiration;
    }
}
