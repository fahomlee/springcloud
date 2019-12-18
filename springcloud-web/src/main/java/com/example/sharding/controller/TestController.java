package com.example.sharding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sharding.interceptor.NoLogin;
import com.example.sharding.ro.LoginRO;
import com.example.sharding.service.ISysUserService;
import com.example.sharding.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/test")
// 动态获取nacos数据
@RefreshScope
@Api
public class TestController {

    @Value("${godlike}")
    private String godlike;

    @Autowired
    private ISysUserService sysUserService;



    @ApiOperation("获取配置参数")
    @RequestMapping(value = "/getConfigValue", method = RequestMethod.GET)
    @ResponseBody
    @NoLogin
    public String getConfigValue() {
        return godlike;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public LoginVO login(LoginRO loginRO) {
        return sysUserService.login(loginRO);
    }

}

