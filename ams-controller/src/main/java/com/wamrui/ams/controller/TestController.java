package com.wamrui.ams.controller;

import com.wamrui.ams.dao.base.crud.Impl.CrudDaoImpl;
import com.wamrui.ams.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    private int i = 0;

    @PostMapping("")
    public String test(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.keySet().forEach(k -> {
            System.out.print(k + ":::" );
            Arrays.asList(parameterMap.get(k)).forEach(s -> {
                System.out.print(s);
            });
            System.out.println();
        });
        return i++ + "";
    }
}
