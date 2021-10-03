package cn.erectpine.system.project.service.impl;

import cn.erectpine.common.core.enums.CodeInfoEnum;
import cn.erectpine.common.core.exception.BusinessException;
import cn.erectpine.common.core.util.pine.Asserts;
import cn.erectpine.common.web.pojo.Result;
import cn.erectpine.dict.api.DictDataApi;
import cn.erectpine.dict.entity.DictData;
import cn.erectpine.system.project.entity.Role;
import cn.erectpine.system.project.mapper.RoleMapper;
import cn.erectpine.system.project.service.IRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-04-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    
    private final RoleMapper roleMapper;
    @Autowired private DictDataApi dictDataApi;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    /**
     * 角色信息-列表
     *
     * @param page  分页参数
     * @param role 查询条件
     * @return 分页列表
     */
    @Override
    public IPage<Role> pageRole(Page<Role> page, Role role) {
        Result<?> result = dictDataApi.pageDictData(new DictData());
        return page(page, Wrappers.lambdaQuery(role));
    }

    /**
     * 根据id获取角色信息表详情
     *
     * @param id id
     * @return {@link Role}
     */
    @Override
    public Role getRoleById(Long id) {
        return getById(id);
    }

    /**
     * 新增-角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void insertRole(Role role) {
        Asserts.isTrue(save(role), () -> new BusinessException(CodeInfoEnum.DATA_INSERT_ERROR));
    }

    /**
     * 修改-角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void updateRole(Role role) {
        Asserts.isTrue(updateById(role), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }

    /**
     * 删除-角色信息
     *
     * @param id id
     */
    @Override
    public void deleteRole(Long id) {
        Asserts.isTrue(removeById(id), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }

}
