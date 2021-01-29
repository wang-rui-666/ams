package com.wamrui.ams.dao.base.crud;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;

public interface CrudDao<T> {
    T insert(T t);
    Collection<T> insert(Collection<T> ts);
    Boolean delete(T t);
    Boolean delete(Collection<T> ts);
    T update(T t);
    Collection<T> update(Collection<T> ts);
    T select(String sql);
    Collection<T> selectList(String sql);
    T select(T t);
    Collection<T> selectList(T t);
    T select(JSONObject json);
    Collection<T> selectList(JSONObject json);
}
