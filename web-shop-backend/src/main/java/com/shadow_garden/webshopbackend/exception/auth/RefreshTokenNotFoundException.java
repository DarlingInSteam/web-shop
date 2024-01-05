package com.shadow_garden.webshopbackend.exception.auth;

public class RefreshTokenNotFoundException extends Exception{
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
}
