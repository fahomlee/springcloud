package com.example.sharding.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.example.sharding.session.HttpSessionUtil;
import com.example.sharding.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录校验拦截器
 * @author xxxxx
 *
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
        log.info("进入登录拦截器LoginInterceptor");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //检查是否配置@NoLogin注解
        NoLogin noLogin=handlerMethod.getMethod().getAnnotation(NoLogin.class);
        if (null != noLogin) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            return false;
        }
        LoginVO loginVO=HttpSessionUtil.getLoginVo();
        return !Objects.isNull(loginVO);
    }

}
