package com.wamrui.ams.rts;

import java.util.Objects;

/*
 * @user WangRui
 * @date 2020/10/29
 * 基本一对一的关系，用于建立两个对象之间的联系 a-a || a-b
 * 维护对象与对象之间的关系。推展map的功能，普通map只能做到通过键取到对应的对象或者集合（严格来说集合也是对象），这里维护的是可以通过 value来获取key或者key的集合。 存入时候，没有key-value之分。
 **/
public class Relationship<T, D> implements Line<T, D> {
    public T t;
    public D d;

    public Relationship() {
        super();
    }

    public Relationship(T t, D d) {
        this();
        this.d = d;
        this.t = t;
    }

    @Override
    public void put(T t, D d) {
        this.t = t;
        this.d = d;
    }

    @Override
    public Object get(Object o) {
        if (o == null) throw new NoKeyException();
        if (o == d) {
            return t;
        } else if ( o== t) {
            return d;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship<?, ?> that = (Relationship<?, ?>) o;
        return (Objects.equals(t, that.t) &&
                Objects.equals(d, that.d)) || (Objects.equals(t, that.d) &&
                Objects.equals(d, that.t));
    }

    @Override
    public int hashCode() {
        return t.hashCode() < d.hashCode() ?
                Objects.hash(t, d) :
                Objects.hash(d, t);
    }
}
