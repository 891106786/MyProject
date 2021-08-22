package com.yx.valid.model;

/**
 * 用于表单验证的通用分组接口
 * @author yx
 * @date 2021/8/5
 */
public interface ValidationInterface {
    /**
     * 新增分组
     */
    interface add{}

    /**
     * 删除分组
     */
    interface delete{}

    /**
     * 查询分组
     */
    interface select{}

    /**
     * 更新分组
     */
    interface update{}
}