package com.wamrui.ams.rts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
 * @user WangRui
 * @date 2020/10/29
 * 关系体网，用户维护不同多个对象组成的联系，
 * 比如a-b，算line
 * a-b a-c a-b算net
 * a-b，a-c，1-2，1-a，算solid
 * 那么 a-b-c，a-b-2，a-b-2-d，就是solidNet
 **/
public class RelationshipSolidMany {
    private final RelationshipSolid relationshipSolid = new RelationshipSolid();

    public void put(Object... os) {
        put(Arrays.asList(os));
    }

    public void put(List os) {
        IntStream.range(0, os.size() - 1).forEach(i ->
                IntStream.range(i + 1, os.size()).forEach(j ->
                        relationshipSolid.put(os.get(i), os.get(j))
                )
        );
    }

    public RelationshipSolid remove(Object... os) {
        return remove(Arrays.asList(os));
    }

    public RelationshipSolid remove(List os) {
        RelationshipSolid re = new RelationshipSolid();
        IntStream.range(0, os.size() - 1).forEach(i ->
                IntStream.range(i + 1, os.size()).forEach(j ->
                        re.put(relationshipSolid.remove(os.get(i), os.get(j)))
                )
        );
        return re;
    }

    public <D> D get(Object t, Class<D> clazz) {
        return relationshipSolid.get(t, clazz);
    }

    public <D> List<D> getList(Object t, Class<D> clazz) {
        return relationshipSolid.getList(t, clazz);
    }

    public Object get(Object t) {
        return relationshipSolid.get(t);
    }

    public List getList(Object t) {
        return relationshipSolid.getList(t);
    }

}


