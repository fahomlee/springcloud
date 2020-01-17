package com.example.sharding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.sharding.interceptor.NoLogin;
import com.example.sharding.ro.LoginRO;
import com.example.sharding.service.CommonService;
import com.example.sharding.service.ISysUserService;
import com.example.sharding.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
// 动态获取nacos数据
@RefreshScope
@Api
@Slf4j
public class TestController {

    @Value("${godlike}")
    private String godlike;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private CommonService commonService;

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

    /**
     * 动态路径（url配置正则表达式）
     * 
     * @param source
     * @param type
     */
    // @PostMapping(value = "/{source:sourceA|sourceB|}/{type}")
    @RequestMapping(value = "/{source:sourceA|sourceB|}/{type}", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public void requestUrl(@PathVariable("source") String source, @PathVariable("type") String type) {
        log.info("请求参数source={},type={}", source, type);
    }

    
    /**
     *多渠道（适用于多种类型业务处理）
     *@param serviceBeanName 类型
     * @return
     */
    @RequestMapping(value = "/testMultiType", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public String testMultiType(String serviceBeanName) {
        return commonService.getString(serviceBeanName);
    }

}

