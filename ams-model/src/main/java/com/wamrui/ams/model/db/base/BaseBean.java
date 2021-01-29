package com.wamrui.ams.model.db.base;

import lombok.Data;

import java.util.List;

@Data
public class BaseBean {
    private Long id;
    private Long createTime;
    private Long updateTime;
    private Boolean deleteFlag;
    //用于记录创建，修改记录，存json字符串，具体待定
    private String info;
    @FieldIgnore
    @DBIgnore
    private List<BaseBean> beans;
}
