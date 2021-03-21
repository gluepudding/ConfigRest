package com.assignment.service;

import com.assignment.utils.AuthEnum;

public interface AuthService {
    AuthEnum authentication(String token);
}
