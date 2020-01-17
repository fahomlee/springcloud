package com.example.sharding.factory;

import com.example.sharding.constant.enums.BaseTypeEnum;
import com.example.sharding.service.BaseService;
import com.example.sharding.session.SpringContextUtil;


public class BaseServiceFactory {

    private static final BaseServiceFactory baseServiceFactory = new BaseServiceFactory();

    public static final BaseServiceFactory getBaseServiceFactory() {
        return baseServiceFactory;
    }

    /**
     * 加载定制的service bean
     * @param baseTypeEnum
     * @return
     */
    public  BaseService getBaseService(BaseTypeEnum baseTypeEnum) {
        BaseService baseService = (BaseService) SpringContextUtil.getBean(baseTypeEnum.getBeanName());
        return baseService;
    }

}
