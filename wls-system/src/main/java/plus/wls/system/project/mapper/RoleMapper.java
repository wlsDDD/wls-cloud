package plus.wls.system.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import plus.wls.system.project.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
