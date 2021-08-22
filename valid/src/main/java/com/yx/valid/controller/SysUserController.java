package com.yx.valid.controller;

import com.yx.valid.exception.BusinessException;
import com.yx.valid.model.SysUser;
import com.yx.valid.model.SysUser001;
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
 * @author yx
 * @date 2021/8/5
 */
@Slf4j
@RestController
@RequestMapping("sys/user")
public class SysUserController {
    private static final List<SysUser> USERS = new ArrayList<>();
    private static final List<SysUser001> USERS001 = new ArrayList<>();

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

    /**
     * 根据手机号或邮箱查询用户信息
     * @param sysUser 查询条件
     * @return 用户list
     */
    @GetMapping
    public CommonResult queryList(@Validated(value = ValidationInterface.select.class) SysUser001 sysUser,
                                  BindingResult result)
    {
        FieldError fieldError = result.getFieldError();

        if(Objects.nonNull(fieldError)) {
            return CommonResult.error(getErrorMsg(fieldError));
        }

        String phone = sysUser.getPhone();
        String email = sysUser.getEmail();

        if(phone == null && email == null) {
            return CommonResult.success(USERS);
        }

        List<SysUser> queryResult = USERS.stream()
                .filter(obj -> obj.getPhone().equals(phone) || obj.getEmail().equals(email))
                .collect(Collectors.toList());
        return CommonResult.success(queryResult);
    }

    /**
     * 新增用户信息
     * @param sysUser 用户信息
     * @return 成功标识
     */
    @PostMapping("insert")
    public CommonResult add(@Validated(value = ValidationInterface.add.class)
                            @RequestBody SysUser001 sysUser,
                            BindingResult result)
    {
        FieldError fieldError = result.getFieldError();

        if(Objects.nonNull(fieldError)) {
            return CommonResult.error(getErrorMsg(fieldError));
        }

        Long id = (long) (USERS.size() + 1);
        sysUser.setId(id);
        USERS001.add(sysUser);
        return CommonResult.success("新增成功");
    }

    /**
     * 根据Id更新用户信息
     * @param sysUser 用户信息
     * @return 成功标识
     */
    @PutMapping("{id}")
    public CommonResult updateById(@PathVariable("id") Long id,
                                   @Validated(value = ValidationInterface.update.class)
                                   @RequestBody SysUser001 sysUser,
                                   BindingResult result)
    {
        FieldError fieldError = result.getFieldError();

        if(Objects.nonNull(fieldError)) {
            return CommonResult.error(getErrorMsg(fieldError));
        }

        for(int i = 0; i < USERS001.size(); i++) {
            if(USERS001.get(i).getId().equals(id)) {
                USERS001.set(i,sysUser);
            }
        }
        return CommonResult.success("更新成功");
    }

    /**
     * 根据Id删除用户信息
     * @param id 主键
     * @return 成功标识
     */
    @DeleteMapping("{id}")
    public CommonResult deleteById(@PathVariable Long id) {
        USERS.removeIf(obj -> obj.getId().equals(id));
        return CommonResult.success("删除成功");
    }

    /**
     * 获取表单验证错误msg
     * @param fieldError 报错字段
     * @return msg
     */
    public String getErrorMsg(FieldError fieldError) {
        String field = fieldError.getField();
        Object rejectedValue = fieldError.getRejectedValue();
        String msg = "[" + fieldError.getDefaultMessage() + "]";
        log.error("{}：字段=={}\t值=={}", msg, field, rejectedValue);
        return msg;
    }
}