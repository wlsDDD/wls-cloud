package cn.erectpine.common.core.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 可序列化的函数式接口
 *
 * @Author wls
 * @Date 2021/4/3 12:25
 */
@FunctionalInterface
public interface FunctionSerializable<T, R> extends Function<T, R>, Serializable {
}
