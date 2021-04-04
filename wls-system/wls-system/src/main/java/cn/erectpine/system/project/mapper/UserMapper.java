package cn.erectpine.system.project.mapper;

import cn.erectpine.system.project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
