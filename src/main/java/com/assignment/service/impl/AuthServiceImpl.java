package com.assignment.service.impl;

import com.assignment.service.AuthService;
import com.assignment.common.bean.AuthEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthEnum authentication(String token) {
        //For demo purpose
        /*
        int index = (int) ( Math.random() * 2 );
        log.info("Current user: " + AuthEnum.values()[index]);
        return AuthEnum.values()[index];
        */
        //For unit test purpose
        return AuthEnum.values()[0];
    }
}
