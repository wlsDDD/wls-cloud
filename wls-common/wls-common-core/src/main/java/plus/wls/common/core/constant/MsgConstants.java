package plus.wls.common.core.constant;

/**
 * 数据操作失败返回提示
 *
 * @author wls
 * @since 2020/9/16 14:21
 */
public interface MsgConstants {
    
    /**
     * 数据库插入失败-提示语
     */
    String INSERT_MSG = "新增数据失败，请联系数据库管理员";
    
    /**
     * 数据库修改失败-提示语
     */
    String UPDATE_MSG = "修改数据失败，请联系数据库管理员";
    
    /**
     * 数据库删除失败-提示语
     */
    String DELETE_MSG = "删除数据失败，请联系数据库管理员";
    
    /**
     * 没有权限操作数据-提示语
     */
    String UNAUTHORIZED_OPERATION = "非法操作";
    
}
