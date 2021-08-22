package com.yx.valid.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 用户信息管理
 * @author yx
 * @date 2021/8/5
 */
@Data
public class SysUser002 implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @NotNull(message = "{id.not.empty}", groups = {ValidationInterface.update.class})
    private Long id;

    /**
     * 用户名
     */
    @NotEmpty(message = "{name.not.empty}", groups = {
            ValidationInterface.update.class,
            ValidationInterface.add.class})
    private String username;

    /**
     * 密码
     */
    @Size(min = 6, max = 16, message = "{password.size.valid}", groups = {
            ValidationInterface.update.class,
            ValidationInterface.add.class})
    private String password = "123456";

    /**
     * 邮箱地址
     */
    @Email(message = "{email.not.valid}",
            groups = {
                    ValidationInterface.update.class,
                    ValidationInterface.add.class,
                    ValidationInterface.select.class})
    @NotEmpty(message = "{email.not.empty}", groups = ValidationInterface.add.class)
    private String email;

    /**
     * 电话
     */
    @Pattern(message = "{phone.not.valid}", regexp = "^1[3456789]\\d{9}$",
            groups = {ValidationInterface.add.class})
    @NotEmpty(message = "{phone.not.empty}", groups = {ValidationInterface.add.class})
    private String phone;
}