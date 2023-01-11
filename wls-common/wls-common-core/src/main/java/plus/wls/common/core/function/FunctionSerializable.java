package plus.wls.common.core.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 可序列化的函数式接口
 *
 * @author wls
 * @since 2021/4/3 12:25
 */
@FunctionalInterface
public interface FunctionSerializable<T, R> extends Function<T, R>, Serializable {

}
