package com.wamrui.ams.controller.base.crud.resolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wamrui.ams.cost.Convert;
import com.wamrui.ams.dao.base.crud.sql.SqlBean;
import com.wamrui.ams.model.db.base.BaseBean;
import com.wamrui.ams.model.db.base.FieldIgnore;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

@Log4j2
public class BaseBeanHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static Class<? extends BaseBean> getClass(String model) throws ClassNotFoundException {
        String modelName = model.substring(0, 1).toUpperCase() + model.substring(1);
        Class obj = Class.forName("com.wamrui.ams.model.db." + SqlBean.lineToHump(modelName)); //以String类型的className实例化类
        return obj;

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return BaseBean.class.isAssignableFrom(parameter.getParameterType()) &&
                parameter.hasParameterAnnotation(BaseBeanParam.class);
    }



    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String model = ((Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                RequestAttributes.SCOPE_REQUEST)).get("model");
        try {
            Class<? extends BaseBean> clazz = getClass(model);
            BaseBean baseBean = clazz.getDeclaredConstructor().newInstance();
            final Map<String, String[]> parameterMap = webRequest.getParameterMap();
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(FieldIgnore.class)) {
                    String fieldName = field.getName();
                    Method method = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
                    String[] vs = parameterMap.get(fieldName);
                    if (vs != null && vs.length > 0) {
                        method.invoke(baseBean, Convert.getObject(vs[0],field.getType()));
                        if (vs.length > 1) {
                            if (baseBean.getBeans() == null) {
                                baseBean.setBeans(new ArrayList());
                            }
                            while (baseBean.getBeans().size() < vs.length - 1) {
                                baseBean.getBeans().add(clazz.getDeclaredConstructor().newInstance());
                            }
                            for (int i = 1; i < vs.length; i++) {
                                method.invoke(baseBean.getBeans().get(i-1), Convert.getObject(vs[i],field.getType()));
                            }
                        }
                    }
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            String msg = String.format("输入model:%s,无效的参数！！！", model);
            throw new RuntimeException(msg);
        }
    }
}
