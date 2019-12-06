package com.example.sharding.service;

import com.example.sharding.ro.LoginRO;
import com.example.sharding.vo.LoginVO;

public interface ISysUserService{
    LoginVO login(LoginRO loginRO);
}