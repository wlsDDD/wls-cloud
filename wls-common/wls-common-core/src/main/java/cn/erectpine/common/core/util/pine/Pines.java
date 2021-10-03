package cn.erectpine.common.core.util.pine;

import cn.erectpine.common.core.exception.RequestHeaderException;
import cn.erectpine.common.core.function.FunctionSerializable;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 核心工具类
 *
 * @author wls
 * @since 2021/09/20 23:41:45
 */
public class Pines {
    
    /**
     * 去除对象中所有String类型的前后空白
     *
     * @param coll coll
     */
    public static void trimBean(Collection<?> coll) {
        coll.forEach(Pines::trimBean);
    }
    
    /**
     * 去除对象中所有String类型的前后空白
     *
     * @param bean bean
     */
    public static void trimBean(Object bean) {
        BeanUtil.getBeanDesc(bean.getClass()).getProps().forEach(propDesc -> {
            if (String.class.getName().equals(propDesc.getFieldType().getTypeName())) {
                Object str = ReflectUtil.invoke(bean, propDesc.getGetter());
                if (str == null) {
                    return;
                }
                ReflectUtil.invoke(bean, propDesc.getSetter(), str.toString().trim());
            }
        });
    }
    
    /**
     * 自定义进制转换
     *
     * @param seq    对应规则的值
     * @param rexStr 规则
     * @return {@link String}
     */
    public static String getSeq(Integer seq, String rexStr) {
        int flag = seq;
        StringBuilder result = new StringBuilder();
        while (true) {
            result.insert(0, rexStr.charAt(seq % rexStr.length()));
            seq = seq / rexStr.length();
            if (seq / rexStr.length() == 0) {
                return flag < rexStr.length() ? result.toString() : rexStr.charAt(seq - 1) + result.toString();
            }
        }
    }
    
    /**
     * 在map中获取指定key 获取不到抛出异常
     *
     * @param map 地图
     * @param key 关键
     * @return {@link V}
     */
    public static <K, V> V getOrException(Map<K, V> map, K key) {
        return Optional.ofNullable(map.get(key)).orElseThrow(() -> new RequestHeaderException(key.toString()));
    }
    
    /**
     * 在map中获取指定key 获取不到抛出异常
     *
     * @param map  地图
     * @param func 函数
     * @return {@link V}
     */
    public static <V, T, R> V getOrException(Map<String, V> map, FunctionSerializable<T, R> func) {
        String key = LamUtil.getFieldName(func);
        return Optional.ofNullable(map.get(key)).orElseThrow(() -> new RequestHeaderException(key));
    }
    
    /**
     * 根据类获取指定所有注解
     *
     * @param clazz 类clazz
     * @param ann   注解clazz
     * @return {@link List<>}
     */
    public static <T extends Annotation> List<T> getFieldAnnotations(Class<?> clazz, Class<T> ann) {
        return BeanUtil.getBeanDesc(clazz).getProps().stream()
                       .map(propDesc -> propDesc.getField().getAnnotation(ann))
                       .filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    /**
     * 浅拷贝-拷贝属性
     * 将old类中的属性拷贝到fresh中
     * 拷贝规则: 属性名相同
     *
     * @param old     源对象
     * @param fresh   拷贝后的对象
     * @param ignores 忽略字段
     * @return fresh
     * @author wls
     */
    @SafeVarargs
    public static <T> T copyBean(Object old, T fresh, FunctionSerializable<T, ?>... ignores) {
        BeanUtil.copyProperties(old, fresh, LamUtil.getFieldNames(ignores));
        return fresh;
    }
    
    /**
     * 得到简单的堆栈跟踪
     *
     * @param e       e
     * @param contain 包含字符串
     * @return {@link StackTraceElement[]}
     */
    public static StackTraceElement[] getSimpleStackTrace(Throwable e, String contain) {
        return Arrays.stream(e.getStackTrace()).distinct().parallel()
                     .filter(item -> item.getLineNumber() != -1 && item.getClassName().contains(contain))
                     .toArray(StackTraceElement[]::new);
    }
    
    /**
     * json 去除json文本中的转义字符
     *
     * @param text 文本
     * @return {@link String}
     */
    public static String jsonDelEscape(String text) {
        return text.replaceAll("\\\\", "")
                   .replaceAll("\"\\{", "{")
                   .replaceAll("\"\\[", "[")
                   .replaceAll("]\"", "]")
                   .replaceAll("}\"", "}");
    }
    
    /**
     * 从字符串中获取数字数字
     *
     * @param str str
     * @return {@link Integer}
     */
    public static Integer getNumeric(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Integer.valueOf(m.replaceAll("").trim());
    }
    
}
