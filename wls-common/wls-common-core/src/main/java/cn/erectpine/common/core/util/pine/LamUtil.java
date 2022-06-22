package cn.erectpine.common.core.util.pine;

import cn.erectpine.common.core.exception.BaseRunTimeException;
import cn.erectpine.common.core.function.FunctionSerializable;
import lombok.extern.slf4j.Slf4j;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * lambda工具类
 *
 * @author wls
 * @since 2021/04/03
 */
@Slf4j
public class LamUtil {
    
    private static final Cache<FunctionSerializable<?, ?>, String> CACHE = new Cache<>(3, TimeUnit.DAYS);
    
    /**
     * 获取字段名s
     *
     * @param func 方法引用型函数
     *
     * @return {@link String[]}
     */
    public static String[] getFieldNames(FunctionSerializable<?, ?>... func) {
        return Arrays.stream(func).map(LamUtil::getFieldName).toArray(String[]::new);
    }
    
    /**
     * 获取字段名
     *
     * @param function 函数
     *
     * @return {@link Field}
     */
    public static <T, R> String getFieldName(FunctionSerializable<T, R> function) {
        return CACHE.computeIfAbsent(function, LamUtil::findFieldName);
    }
    
    /**
     * 反射获取字段名
     *
     * @return {@link String }
     *
     * @author wls
     * @date 2022-06-22 11:03:01
     * @since 1.0.0
     */
    private static String findFieldName(FunctionSerializable<?, ?> function) {
        SerializedLambda serializedLambda;
        try {
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            serializedLambda = (SerializedLambda) method.invoke(function);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new BaseRunTimeException(e);
        }
        String implMethodName = serializedLambda.getImplMethodName();
        if (implMethodName.startsWith("get") && implMethodName.length() > 3) {
            return Introspector.decapitalize(implMethodName.substring(3));
        } else if (implMethodName.startsWith("is") && implMethodName.length() > 2) {
            return Introspector.decapitalize(implMethodName.substring(2));
        } else if (implMethodName.startsWith("lambda$")) {
            throw new IllegalArgumentException("FunctionSerializable不能传递lambda表达式,只能使用方法引用");
        } else {
            throw new IllegalArgumentException(implMethodName + "不是Getter方法引用");
        }
    }
    
}