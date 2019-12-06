package com.example.sharding.session;

import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.example.sharding.constant.Constant;
import com.example.sharding.vo.LoginVO;

/**
 * session工具类
 *
 */
public class HttpSessionUtil {

    /**
          * 获取当前登录用户
     * 
     * @return
     */
    public static LoginVO getLoginVo() {
        // 请求头上要带上token
        String token = getHttpServletRequest().getHeader("token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String data = getRedisService().opsForValue().get(Constant.USER_SESSION + "_" + token);
        if (StringUtils.isBlank(data)) {
            return null;
        }
        byte[] b = Base64.getDecoder().decode(data);
        LoginVO loginVO = JSON.parseObject(b, LoginVO.class);
        return loginVO;
    }

    /**
     * HttpServletRequest
     * 
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     * HttpServletResponse
     * 
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * StringRedisTemplate
     * 
     * @return
     */
    private static StringRedisTemplate getRedisService() {
        return (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
    }
}
