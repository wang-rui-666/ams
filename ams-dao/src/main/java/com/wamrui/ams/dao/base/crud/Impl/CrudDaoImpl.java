package com.wamrui.ams.dao.base.crud.Impl;

import com.alibaba.fastjson.JSONObject;
import com.wamrui.ams.dao.base.crud.CrudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public class CrudDaoImpl<T> implements CrudDao<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public T insert(T t) {
        return null;
    }

    @Override
    public Collection<T> insert(Collection<T> ts) {
        return null;
    }

    @Override
    public Boolean delete(T t) {
        return null;
    }

    @Override
    public Boolean delete(Collection<T> ts) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public Collection<T> update(Collection<T> ts) {
        return null;
    }

    @Override
    public T select(String sql) {
        return null;
    }

    @Override
    public Collection<T> selectList(String sql) {
        return null;
    }

    @Override
    public T select(T t) {
        return null;
    }

    @Override
    public Collection<T> selectList(T t) {
        return null;
    }

    @Override
    public T select(JSONObject json) {
        return null;
    }

    @Override
    public Collection<T> selectList(JSONObject json) {
        return null;
    }
}
