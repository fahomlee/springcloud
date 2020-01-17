package com.example.sharding.constant.enums;

/**
 * service bean枚举
 * 
 * @author fahomlee
 *
 */
public enum BaseTypeEnum {
    A("baseAService"), B("baseBService"), DEFAULT("baseAService");

    private String beanName;

    BaseTypeEnum(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
    /**
     * 根据serviceBeanName获取BaseTypeEnum
     * @param serviceBeanName
     * @return
     */
   public static BaseTypeEnum getBaseTypeEnum (String serviceBeanName) {
        for(BaseTypeEnum b:values()) {
            if(b.getBeanName().equals(serviceBeanName)) {
                return b;
            }
        }
        return BaseTypeEnum.DEFAULT;
    }
}
