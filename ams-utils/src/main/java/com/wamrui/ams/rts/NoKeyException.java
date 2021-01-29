package com.wamrui.ams.rts;
/*
 * @user WangRui
 * @date 2020/10/29
 * 没有传入key值
**/
public class NoKeyException extends RuntimeException {
    public NoKeyException(){
        super("no key for instance");
    }
}
