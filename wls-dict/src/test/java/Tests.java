import cn.erectpine.common.gencode.MysqlGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author wls
 * @since 2021/9/5 0:23
 */
public class Tests {
    @Test
    public void test01() {
        MysqlGenerator.generatorCode("cn.erectpine.dict.project", "sys_dict_data");
    }
    
}
