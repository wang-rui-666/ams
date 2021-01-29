package com.wamrui.ams.rts;

import java.util.*;
import java.util.stream.Collectors;

/*
 * @user WangRui
 * @date 2020/10/29
 * 关系网，维护多个对象之间的联系 a-b a-c b-c,
 * 注意，这里维护了类型，所以传入类型 必须一致，int-double和double-int算一致
 **/
public class RelationshipNet<T, D> implements Net<T, D> {
    Set<Relationship<T, D>> relationships = new HashSet<>();
    private Class t;
    private Class d;

    @Override
    public void put(T t, D d) {
        put(new Relationship<T, D>(t, d));
    }

    public void put(Relationship relationship) {
        this.t = relationship.t.getClass();
        this.d = relationship.d.getClass();
        relationships.add(relationship);
    }

    @Override
    public List get(Object o) {
        return relationships.
                stream().
                map(relationship -> relationship.get(o)).
                filter(Objects::nonNull).
                collect(Collectors.toList());
    }

    @Override
    public Relationship remove(T t, D d) {
        Relationship relationship =  new Relationship<T, D>(t, d);
        relationships.remove(relationship);
        return relationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationshipNet<?, ?> that = (RelationshipNet<?, ?>) o;
        return Objects.equals(t, that.t) &&
                Objects.equals(d, that.d);
    }

    @Override
    public int hashCode() {
        return t.hashCode() < d.hashCode() ?
                Objects.hash(t, d) :
                Objects.hash(d, t);
    }
}
