import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.junit.jupiter.api.Test;
import plus.wls.common.core.function.FunctionSerializable;
import plus.wls.common.core.util.LamUtil;
import plus.wls.system.project.entity.User;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

class test01 {
    
    
    String url = "jdbc:mysql://rm-uf66903d9ae3a6k15fo.mysql.rds.aliyuncs.com:3306/wls-nacos?" +
            "useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    
    @Test
    void test02() {
        String implMethodName = LambdaUtils.extract(User::getUserId).getImplMethodName();
        String fieldName1 = LamUtil.fieldName(User::getNickName);
        String s = PropertyNamer.methodToProperty(implMethodName);
        String fieldName = getFieldName(User::getUserId);
    }
    
    
    /**
     * 获取字段名
     *
     * @param func 函数
     *
     * @return {@link String}
     */
    public static <T, R> String getFieldName(FunctionSerializable<T, R> func) {
        try {
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            String name = ((SerializedLambda) method.invoke(func)).getImplMethodName();
            if (name.startsWith("is")) {
                name = name.substring(2);
            } else {
                if (!name.startsWith("get") && !name.startsWith("set")) {
                    throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
                }
                name = name.substring(3);
            }
            if (name.length() == 1 || name.length() > 1 && !Character.isUpperCase(name.charAt(1))) {
                name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
            }
            return name;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void test01() {
        FastAutoGenerator.create(url, "ming", "Ming2020--Yike")
                         .globalConfig(builder -> builder.author("wls")
                                                         .enableSwagger()
                                                         .outputDir(System.getProperty("user.dir"))
                         )
                         .packageConfig(builder -> builder.parent("plus.wls.common.gencode")
                                                          .moduleName("demo")
                         )
                         .strategyConfig(builder -> builder.addInclude("dict_data")
                                                           .addTablePrefix("t_", "c_")
                         )
                         .execute();
    }
    
}
