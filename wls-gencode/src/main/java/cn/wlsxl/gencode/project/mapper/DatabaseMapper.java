package cn.wlsxl.gencode.project.mapper;

import cn.wlsxl.gencode.project.entity.Database;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据库连接配置表 Mapper 接口
 * </p>
 *
 * @author wls
 * @since 2022-07-18
 */
@Mapper
public interface DatabaseMapper extends BaseMapper<Database> {

}
