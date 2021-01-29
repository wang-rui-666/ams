package com.wamrui.ams.controller.base.crud.impl;

import com.wamrui.ams.controller.base.crud.CrudController;
import com.wamrui.ams.dao.base.crud.sql.SqlBean;
import com.wamrui.ams.model.db.base.BaseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping
public class CurdControllerImpl implements CrudController {




    @Override
    @PostMapping({"/{model}/i", "/{model}/in", "/{model}/ins", "/{model}/inst", "/{model}/insert"})
    public Object insert(HttpServletRequest request, @PathVariable String model) {


        return null;
    }

    @Override
    @PostMapping({"/{model}/d", "/{model}/de", "/{model}/del", "/{model}/det", "/{model}/delete"})
    public Boolean delete(HttpServletRequest request, @PathVariable String model) {
        return null;
    }

    @Override
    @PostMapping({"/{model}/u", "/{model}/up", "/{model}/upd", "/{model}/upt", "/{model}/update"})
    public Object update(HttpServletRequest request, @PathVariable String model) {
        return null;
    }

    @Override
    @PostMapping({"/{model}/s", "/{model}/se", "/{model}/sel", "/{model}/slt", "/{model}/select"})
    public Object select(HttpServletRequest request, @PathVariable String model) {
        return null;
    }

    @Override
    @PostMapping({"/{model}/ss", "/{model}/ses", "/{model}/sell", "/{model}/selectList"})
    public Collection selectList(HttpServletRequest request, @PathVariable String model) {
        return null;
    }
}
