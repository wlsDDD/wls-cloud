package cn.erectpine.system.project.entity.vo;

import cn.erectpine.system.project.entity.User;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wls
 * @since 2021/11/1 14:03
 */
public class UserVo {
    
    @Valid
    List<User> users;
    
}
