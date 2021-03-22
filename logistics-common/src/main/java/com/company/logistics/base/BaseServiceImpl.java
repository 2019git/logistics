package com.company.logistics.base;

import com.company.logistics.util.BaseEntityUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wzj
 * @date 2020/12/24 22:49
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T>{

    @Autowired
    protected M mapper;

    protected Class<T> rawType;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        rawType =getEntityClass();
    }

    private Class<T> getEntityClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public T selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectByIds(Collection<Long> ids) {
        Example example = new Example(rawType);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectList() {
        Example example = new Example(rawType);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted","0");
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> selectList(Map<String, Object> params) {
        Example example = new Example(rawType);
        Example.Criteria criteria = example.createCriteria();
        params.forEach(criteria::andEqualTo);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Long selectCount() {
        return (long) mapper.selectAll().size();
    }

    @Override
    public Long selectCount(T t) {
        return (long) mapper.selectCount(t);
    }

    @Override
    public void insert(T t) {
        BaseEntityUtil.createEntityInfo(t);
        mapper.insert(t);
    }

    @Override
    public void insertAll(Collection<T> entities) {
        entities.forEach(this::insert);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAllById(Collection<Long> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public void delete(T t) {
        mapper.delete(t);
    }

    @Override
    public void logicDeleteById(Long id) {
        T t = mapper.selectByPrimaryKey(id);
        t.setDeleted(Boolean.TRUE);
        mapper.updateByPrimaryKey(t);
    }

    @Override
    public void logicDeleteAllById(Collection<Long> ids) {
        ids.forEach(this::logicDeleteById);
    }

    @Override
    public void updateById(T t) {
        BaseEntityUtil.updateEntityInfo(t);
        mapper.updateByPrimaryKey(t);
    }

    @Override
    public void updateAllById(List<T> entities) {
        entities.forEach(this::updateById);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Long selectCountByExample(Example example) {
        return (long)mapper.selectCountByExample(example);
    }

    @Override
    public List<T> selectBySearchInfo(Map<String, Object> params) {
        Example example = new Example(rawType);
        Example.Criteria criteria = example.createCriteria();
        params.forEach((k,v) -> {
            criteria.andLike(k,v.toString() + "%");
        });
        example.setOrderByClause("update_date DESC");
        return mapper.selectByExample(example);
    }

    @Override
    public T queryBySearchInfo(Map<String, Object> params) {
        List<T> ts = this.selectBySearchInfo(params);
        if (CollectionUtils.isEmpty(ts)){
            return null;
        }
        return ts.get(0);
    }

    @Override
    public void insertOrUpdate(T t) {
        if (null == t.getId()){
            this.insert(t);
        }else {
            this.updateById(t);
        }
    }
}
