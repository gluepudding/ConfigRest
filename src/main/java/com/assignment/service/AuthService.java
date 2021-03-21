package com.assignment.service;

import com.assignment.common.bean.AuthEnum;

public interface AuthService {
    AuthEnum authentication(String token);
}
