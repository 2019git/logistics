package com.company.logistics.base;

import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wzj
 * @date 2020/12/24 22:47
 */
public interface BaseService<T extends BaseEntity> {

    T selectById(Long id);

    List<T> selectByIds(Collection<Long> ids);

    List<T> selectList();

    List<T> selectList(T t);

    List<T> selectList(Map<String,Object> params);

    List<T> selectAll();

    Long selectCount();

    Long selectCount(T t);

    void insert(T t);
    
    void insertAll(Collection<T> entities);

    void deleteById(Long id);

    void deleteAllById(Collection<Long> ids);

    void delete(T t);

    void logicDeleteById(Long id);

    void logicDeleteAllById(Collection<Long> ids);

    void updateById(T t);

    void updateAllById(List<T> entities);

    List<T> selectByExample(Example example);

    Long selectCountByExample(Example example);

    List<T> selectBySearchInfo(Map<String, Object> params);

    T queryBySearchInfo(Map<String, Object> params);

    void insertOrUpdate(T t);
}
