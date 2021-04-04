import cn.erectpine.common.enums.CodeMsgEnum;
import cn.erectpine.common.enums.ServeEnum;
import cn.erectpine.common.util.Assert;
import cn.erectpine.common.util.CoreUtil;
import cn.erectpine.common.web.exception.BaseRunTimeException;
import cn.erectpine.common.web.exception.BusinessException;
import cn.erectpine.system.project.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Author wls
 * @Date 2021/1/12 14:36
 */
@Slf4j
public class Tests {
    
    @Test
    public void test04() {
        Assert.isZero(1, () -> new BusinessException(CodeMsgEnum.ARG_VERIFY_ERROR, "id不能为null"));
    }
    
    @Test
    public void test03() {
        System.out.println("ServeEnum.SYSTEM.name() = " + ServeEnum.SYSTEM.name());
        System.out.println("ServeEnum.SYSTEM = " + ServeEnum.SYSTEM.getServeName());
    }
    
    @Test
    public void test02() {
        Assert.isTrue(true, () -> new BaseRunTimeException("hello"));
        Assert.isNull(null, () -> new BaseRunTimeException("s"));
        Assert.isTrue(true, () -> new BaseRunTimeException(CodeMsgEnum.DATA_DELETE_ERROR));
    }
    
    @Test
    public void test01() {
        User wls = new User().setUserId(1L).setUserName("wls").setNickName("nick-wls");
//        User nickName = CoreUtil.convertFor(wls, new User(), User::getNickName);
        User user = CoreUtil.copyBean(wls, new User(), User::getNickName);
        
    }
    
}
