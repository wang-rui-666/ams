package com.wamrui.ams.controller.base.crud;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public interface CrudController {
    /**
     * 根据属性插入
     *
     * @param request 请求传入参数
     * @param model 要操作的表
     * @return 一条或者多条
     */
    Object insert(HttpServletRequest request, String model);

    /**
     * 根据条件删除
     *
     * @param request 请求传入参数
     * @param model 要操作的表
     * @return 是否
     */
    Boolean delete(HttpServletRequest request, String model);

    /**
     * 根据id 更新
     *
     * @param request 请求传入参数
     * @param model 要操作的表
     * @return 一条或者多条
     */
    Object update(HttpServletRequest request, String model);

    /**
     * 根据条件取出
     *
     * @param request 请求传入参数
     * @param model 要操作的表
     * @return 一条
     */
    Object select(HttpServletRequest request, String model);

    /**
     * 根据条件取出
     *
     * @param request 请求传入参数
     * @param model 要操作的表
     * @return 多条
     */
    Collection selectList(HttpServletRequest request, String model);
}
