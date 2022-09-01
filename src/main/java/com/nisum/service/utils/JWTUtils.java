package com.nisum.service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nisum.service.presentation.presenter.UserPresenter;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    public static String addToken(UserPresenter userPresenter) {
        String sign = "P@tit0";
        long EXPIRATION_DATE = 28_800_000;
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("user", userPresenter);
        return JWT.create()
                .withSubject(userPresenter.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(sign));
    }
}
