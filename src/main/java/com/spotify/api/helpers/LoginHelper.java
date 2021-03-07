package com.spotify.api.helpers;

import com.spotify.api.services.AuthService;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoginHelper {
    private final AuthService authService = new AuthService();

    public String getToken() {
        return authService.refreshToken().accessToken();
    }
}
