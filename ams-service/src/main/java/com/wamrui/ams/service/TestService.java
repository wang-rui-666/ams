package com.wamrui.ams.service;

import com.wamrui.ams.dao.base.crud.Impl.CrudDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    CrudDaoImpl crudDao;
    private int a = 0;

    public void outA() {

    }
}
