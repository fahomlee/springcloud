package com.example.sharding.service.impl;

import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharding.constant.Constant;
import com.example.sharding.mapper.SysUserMapper;
import com.example.sharding.model.SysUser;
import com.example.sharding.ro.LoginRO;
import com.example.sharding.service.ISysUserService;
import com.example.sharding.vo.LoginVO;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public LoginVO login(LoginRO loginRO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq("id", loginRO.getId());
        queryWrapper.eq("name", loginRO.getName());
        SysUser sysUser = getOne(queryWrapper);
        LoginVO loginVO = new LoginVO();
        if (!Objects.isNull(sysUser)) {
            BeanUtils.copyProperties(sysUser, loginVO);
        }
        // 登录token 保存30分钟
        String token = RandomStringUtils.randomAlphanumeric(64);
        redisTemplate.opsForValue().set(Constant.USER_SESSION + "_" + token,
                        Base64.getEncoder().encodeToString(JSON.toJSONBytes(loginVO)), Constant.SESSION_TIME_OUT,
                        TimeUnit.SECONDS);
        loginVO.setToken(token);
        return loginVO;
    }

}
