package com.example.sharding.service;

import org.springframework.stereotype.Service;
import com.example.sharding.constant.enums.BaseTypeEnum;
import com.example.sharding.factory.BaseServiceFactory;

@Service
public class CommonService {

    public String getString(String serviceBeanName) {
        //加载对应业务的service bean
        BaseService baseService = BaseServiceFactory.getBaseServiceFactory().getBaseService(BaseTypeEnum.getBaseTypeEnum(serviceBeanName));
        return baseService.getBase();
    }

}
