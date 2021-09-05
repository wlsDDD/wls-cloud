import cn.erectpine.common.gencode.MysqlGenerator;
import org.junit.jupiter.api.Test;

/**
 * @Author wls
 * @Date 2021/9/5 0:23
 */
public class Tests {
    @Test
    public void test01() {
        MysqlGenerator.generatorCode("sys_dict_data");
    }
    
}
