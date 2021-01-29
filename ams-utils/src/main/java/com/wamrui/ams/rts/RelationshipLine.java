package com.wamrui.ams.rts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/*
 * @user WangRui
 * @date 2020/10/29
 * 建立两个对象之间的联系
**/
public class RelationshipLine <T,D> implements Line<T , D >{
    private final Relationship<T,D> relationship = new Relationship<>();


    @Override
    public void put(T t, D d) {
        relationship.put(t,d);
    }

    @Override
    public Object get(Object o) {
        return relationship.get(o);
    }

}
