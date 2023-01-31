package plus.wls.common.web.valid;

import cn.hutool.core.util.EnumUtil;
import plus.wls.common.web.annotation.EnumValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数验证
 * 只能为枚举指定值
 *
 * @author wls
 * @since 2021/11/1 14:14
 */
public class ParamValid implements ConstraintValidator<EnumValid, Object> {
    
    private Set<String> ordinal;
    
    @Override
    public void initialize(EnumValid constraintAnnotation) {
        ordinal = EnumUtil.getFieldValues(constraintAnnotation.value(), constraintAnnotation.fieldName()).stream().map(o -> o + "")
                          .collect(Collectors.toSet());
    }
    
    /**
     * 具体校验规则
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return ordinal.contains(value + "");
    }
    
}
