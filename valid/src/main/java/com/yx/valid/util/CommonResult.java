package com.yx.valid.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作消息提醒
 * @author yx
 * @date 2021/8/5
 */
@Data
@NoArgsConstructor
public class CommonResult {
    /** 状态码 */
    private int code;

    /** 返回内容 */
    private String msg;

    /** 数据对象 */
    private Object data;

    /**
     * 初始化一个新创建的 CommonResult 对象
     * @param type 状态类型
     * @param msg 返回内容
     */
    public CommonResult(Type type, String msg) {
        this.code = type.value;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public CommonResult(Type type, String msg, Object data) {
        this.code = type.value;
        this.msg = msg;
        if (data != null) {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static CommonResult success() {
        return CommonResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * @return 成功消息
     */
    public static CommonResult success(Object data) {
        return CommonResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static CommonResult success(String msg) {
        return CommonResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static CommonResult success(String msg, Object data) {
        return new CommonResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static CommonResult warn(String msg) {
        return CommonResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static CommonResult warn(String msg, Object data) {
        return new CommonResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * @return 错误信息
     */
    public static CommonResult error() {
        return CommonResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 错误消息
     */
    public static CommonResult error(String msg) {
        return CommonResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static CommonResult error(String msg, Object data) {
        return new CommonResult(Type.ERROR, msg, data);
    }

    /**
     * 状态类型
     */
    public enum Type {
        /** 成功 */
        SUCCESS(200),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value){
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}