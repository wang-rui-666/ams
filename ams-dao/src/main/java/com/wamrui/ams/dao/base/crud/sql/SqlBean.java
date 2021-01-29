package com.wamrui.ams.dao.base.crud.sql;

import com.wamrui.ams.model.db.base.BaseBean;
import com.wamrui.ams.model.db.base.DBIgnore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SqlBean<T extends BaseBean> {
    private final Class clazz;
    private final Boolean change;
    private final String tableName;
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public SqlBean(Class<T> clazz, Boolean change, String tableName) {
        this.clazz = clazz;
        this.change = change;
        this.tableName = tableName;
    }

    public SqlBean(Class<T> clazz, Boolean change) {
        this(clazz, change, change ? humpToLine2(clazz.getSimpleName()) : clazz.getSimpleName());
    }

    public SqlBean(Class<T> clazz, String tableName) {
        this(clazz, true, tableName);
    }

    public SqlBean(Class<T> clazz) {
        this(clazz, true);
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public void bean2fs$vs(List<String> fs, List<String> vs, T t) {
        fs.clear();
        vs.clear();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Arrays.asList(declaredFields).
                stream().
                filter(f -> !f.isAnnotationPresent(DBIgnore.class)).
                forEach(f -> {
                    String fn = f.getName();
                    try {
                        Method method = t.
                                getClass().
                                getMethod("get" + fn.substring(0, 1).toUpperCase() + fn.substring(1));
                        Object rt = method.
                                invoke(t);
                        if (rt != null) {
                            fs.add(change ? humpToLine2(fn) : fn);
                            if (String.class.isInstance(rt) ||
                                    char.class.isInstance(rt)) {
                                vs.add("'" + rt.toString() + "'");
                            } else if (Boolean.class.isInstance(rt)) {
                                if ((Boolean) rt) {
                                    vs.add("1");
                                } else {
                                    vs.add("0");
                                }
                            } else {
                                vs.add(rt.toString());
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String bean2insert(T t) {
        if (t == null) return null;
        List<String> fs = new ArrayList();
        List<String> vs = new ArrayList();
        bean2fs$vs(fs, vs, t);
        StringBuffer sb = new StringBuffer();
        sb.
                append("insert into ").
                append(tableName).
                append(" (").
                append(fs.stream().collect(Collectors.joining(","))).
                append(")").
                append(" values(").
                append(vs.stream().collect(Collectors.joining(","))).
                append(");");
        return sb.toString();
    }

    public String bean2where(T t) {
        if (t == null) return null;
        if (t.getId() != null && t.getId() > 0) {
            return "where id = " + t.getId();
        }
        List<String> fs = new ArrayList();
        List<String> vs = new ArrayList();
        bean2fs$vs(fs, vs, t);
        StringBuffer sb = new StringBuffer();
        sb.append("where ");
        IntStream.range(0, fs.size()).forEach(i -> sb.append(fs.get(i) + "=" + vs.get(i) + " "));
        return sb.toString();
    }

    public String bean2update(T t) {
        if (t == null || t.getId() == null || t.getId() == 0l) {
            return null;
        }
        List<String> fs = new ArrayList();
        List<String> vs = new ArrayList();
        bean2fs$vs(fs, vs, t);
        StringBuffer sb = new StringBuffer();
        sb.
                append("update ").
                append(tableName).
                append("set ");
        IntStream.range(0, fs.size()).forEach(i -> sb.append(fs.get(i) + "=" + vs.get(i) + " "));
        sb.append(bean2where(t));
        return sb.toString();
    }


    public String bean2delete(T t) {
        if (t == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.
                append("update ").
                append(tableName).
                append("set delete_flag = 1 ").
                append(bean2where(t));
        return sb.toString();
    }
}
