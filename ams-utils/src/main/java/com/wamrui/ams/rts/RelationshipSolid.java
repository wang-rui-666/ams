package com.wamrui.ams.rts;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 * @user WangRui
 * @date 2020/10/29
 * 关系体，用于维护不同对象间的联系，
 * 这里拓展了class，可以传入不同的类型
 **/
public class RelationshipSolid implements Solid {
    Map<Key, RelationshipNet> map = new ConcurrentHashMap();

    @Override
    public <T, D> void put(T t, D d) {
        put(new Relationship(t, d));
    }

    public <T, D> void put(Relationship<T, D> relationship) {
        Key key = Key.product(relationship.t.getClass(), relationship.d.getClass());
        synchronized (this) {
            if (map.get(key) == null) {
                map.put(key, new RelationshipNet());
            }
            map.get(key).put(relationship);
        }
    }


    @Override
    public <D> D get(Object t, Class<D> clazz) {
        List<D> list = getList(t, clazz);
        if (list == null) return null;
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public <D> List<D> getList(Object t, Class<D> clazz) {
        Key key = Key.product(t.getClass(), clazz);
        if (map.get(key) == null) return null;
        return map.get(key).get(t);
    }

    @Override
    public Object get(Object t) {
        List list = getList(t);
        if (list == null) return null;
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List getList(Object t) {
        List re = new ArrayList();
        if (map.values().size() == 0) return re;
        Iterator<RelationshipNet> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            re.addAll(iterator.next().get(t));
        }
        return re;
    }

    @Override
    public <T, D> Relationship<T, D> remove(T t, D d) {
        Key key = Key.product(t.getClass(), d.getClass());
        if (map.get(key) == null) {
            return new Relationship<>(t, d);
        }
        Relationship<T, D> line = map.get(key).remove(t, d);
        if (map.get(key).relationships.size() == 0) {
            map.remove(key);
        }
        return line;
    }


}
