package com.company.logistics.base;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author wzj
 * @date 2020/12/24 22:45
 */
public interface BaseMapper<T extends BaseEntity> extends Mapper<T> {
}
