package com.yx.valid.controller;

import com.yx.valid.exception.BusinessException;
import com.yx.valid.model.SysUser;
import com.yx.valid.model.ValidationInterface;
import com.yx.valid.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户管理控制器
 *
 * @author ludangxin
 * @date 2021/8/5
 */
@Slf4j
@RestController
@RequestMapping("sys/user")
public class SysUserController {
    private static final List<SysUser> USERS = new ArrayList<>();

    // 数据初始化
    static {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("zhangsan");
        user.setPhone("13566666666");
        user.setEmail("example@qq.com");
        USERS.add(user);
        SysUser user1 = new SysUser();
        user1.setId(2L);
        user1.setUsername("lisi");
        user1.setPhone("13588888888");
        user1.setEmail("example1@qq.com");
        USERS.add(user1);
    }

    /**
     * 新增用户信息
     * @param sysUser 用户信息
     * @return 成功标识
     */
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysUser sysUser, BindingResult result) {
        FieldError fieldError = result.getFieldError();

        if(Objects.nonNull(fieldError)) {
            String field = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            String msg = "[" + fieldError.getDefaultMessage() + "]";
            log.error("{}：字段=={}\t值=={}", msg, field, rejectedValue);
            return CommonResult.error(msg);
        }

        USERS.add(sysUser);
        return CommonResult.success("新增成功");
    }
}