package cn.wlsxl.gencode.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库连接配置表
 * </p>
 *
 * @author wls
 * @since 2022-07-18
 */
@Data
@Accessors(chain = true)
@TableName("gen_database")
public class Database implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键、自动增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据库连接别名
     */
    private String name;

    /**
     * 完整数据库连接（包含host port name）
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    
}
