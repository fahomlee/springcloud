package com.example.sharding.service.impl;

import org.springframework.stereotype.Service;
import com.example.sharding.service.BaseService;

@Service
public class BaseAService implements BaseService {

    @Override
    public String getBase() {
        return "A";
    }

}
