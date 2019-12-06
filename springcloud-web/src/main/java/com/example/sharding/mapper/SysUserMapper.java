
package com.example.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharding.model.SysUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fahomelee
 * @since 2019-05-29
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    
}

