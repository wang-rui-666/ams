package com.wamrui.ams.rts;

import java.util.*;
import java.util.stream.Collectors;

public class Key {
    Set<Object> os;

    public Key() {
        super();
    }

    public Key(Set set) {
        this();
        this.os = set;
    }

    public static Key product(Object... os) {
        Set set = new HashSet();
        for (Object o : os)
            set.add(o);
        return new Key(set);
    }

    ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        if(key.os == null) return false;
        boolean f = false;
        for (Object o1 : key.os) {
            if (os.contains(o1)) {
                f = true;
            }
        }
        return f && key.os.size() == os.size();
    }

    @Override
    public int hashCode() {
        if (os == null)
            return 0;
        int result = 1;
        List<Integer> ints = os.
                stream().
                map(Object::hashCode).
                collect(Collectors.toList());
        Collections.sort(ints);
        for (Object element : ints)
            result = 31 * result + (element == null ? 0 : element.hashCode());
        return result;
    }
}
