package com.wamrui.ams.rts;
/*
 * @user WangRui
 * @date 2020/10/29
 * 关系
**/
public interface Line<T extends  Object, D extends Object> {
    /*
     * @user WangRui
     * @date 2020/10/29
     * 把相关的两个对象联系起来
    **/
    void put(T t, D d);
    /*
     * @user WangRui
     * @date 2020/10/29
     * 放入一个对象，获取另一个对象
    **/
    Object get(Object o);



}
