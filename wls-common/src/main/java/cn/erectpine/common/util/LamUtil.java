package cn.erectpine.common.util;

import cn.erectpine.common.function.FunctionSerializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * lambda工具类
 *
 * @author wls
 * @date 2021/04/03
 */
@Slf4j
public class LamUtil {
    
    private static final Map<FunctionSerializable<?, ?>, Field> CACHE = new ConcurrentHashMap<>();
    
    /**
     * 获取字段名
     *
     * @param function 函数
     * @return {@link String}
     */
    public static <T, R> String getFieldName(FunctionSerializable<T, R> function) {
        Field field = LamUtil.getField(function);
        return field.getName();
    }
    
    /**
     * 获取字段
     *
     * @param function 函数
     * @return {@link Field}
     */
    private static Field getField(FunctionSerializable<?, ?> function) {
        return CACHE.computeIfAbsent(function, LamUtil::findField);
    }
    
    private static Field findField(FunctionSerializable<?, ?> function) {
        Field field = null;
        String fieldName = null;
        try {
            // 第1步 获取SerializedLambda
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            // 第2步 implMethodName 即为Field对应的Getter方法名
            String implMethodName = serializedLambda.getImplMethodName();
            if (implMethodName.startsWith("get") && implMethodName.length() > 3) {
                fieldName = Introspector.decapitalize(implMethodName.substring(3));
            } else if (implMethodName.startsWith("is") && implMethodName.length() > 2) {
                fieldName = Introspector.decapitalize(implMethodName.substring(2));
            } else if (implMethodName.startsWith("lambda$")) {
                throw new IllegalArgumentException("FunctionSerializable不能传递lambda表达式,只能使用方法引用");
            } else {
                throw new IllegalArgumentException(implMethodName + "不是Getter方法引用");
            }
            // 第3步 获取的Class是字符串，并且包名是“/”分割，需要替换成“.”，才能获取到对应的Class对象
            String declaredClass = serializedLambda.getImplClass().replace("/", ".");
            Class<?> aClass = Class.forName(declaredClass, false, ClassUtils.getDefaultClassLoader());
            // 第4步  Spring 中的反射工具类获取Class中定义的Field
            field = ReflectionUtils.findField(aClass, fieldName);
        } catch (Exception e) {
            log.error("类型转换异常", e);
        }
        // 第5步 如果没有找到对应的字段应该抛出异常
        if (field != null) {
            return field;
        }
        throw new NoSuchFieldError(fieldName);
    }
}