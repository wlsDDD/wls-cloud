package cn.erectpine.system.project.mapper;

import cn.erectpine.system.project.entity.UserPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Mapper
public interface UserPostMapper extends BaseMapper<UserPost> {

}
