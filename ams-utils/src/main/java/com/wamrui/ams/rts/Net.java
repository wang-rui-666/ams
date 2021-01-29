package com.wamrui.ams.rts;

import java.util.List;
/*
 * @user WangRui
 * @date 2020/10/29
 * 维护两个对象之间的联系，多对多
**/
public interface Net <T,D> {
    void put(T t,D d);
    List get(Object o);
    Relationship remove(T t,D d);
}
