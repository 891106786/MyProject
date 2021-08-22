package com.yx.valid.validation;

import javax.validation.ConstraintValidator;
import com.yx.valid.annotation.Phone;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 手机号校验器
 * @author yx
 * @date 2021/8/7
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    /**
     * 手机号正则表达式
     */
    private static final String REGEXP_PHONE = "^1[3456789]\\d{9}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(Objects.isNull(value)) {
            return true;
        }

        return Pattern.matches(REGEXP_PHONE, value);
    }
}
