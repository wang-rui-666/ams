package com.wamrui.ams.rts;

import java.util.List;

public interface Solid {
    <T ,D> void  put(T t,D d);
    <D> D  get(Object t, Class<D> clazz);
    <D> List<D> getList(Object t, Class<D> clazz);
    Object get(Object t);
    List getList(Object t);
    <T ,D>Relationship<T ,D> remove(T t,D d);
}
