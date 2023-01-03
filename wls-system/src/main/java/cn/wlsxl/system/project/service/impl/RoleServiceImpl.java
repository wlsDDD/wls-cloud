package cn.wlsxl.system.project.service.impl;

import cn.wlsxl.common.core.enums.CodeInfoEnum;
import cn.wlsxl.common.core.exception.BusinessException;
import cn.wlsxl.common.core.util.Asserts;
import cn.wlsxl.common.web.util.PageUtil;
import cn.wlsxl.system.project.entity.Role;
import cn.wlsxl.system.project.mapper.RoleMapper;
import cn.wlsxl.system.project.service.IRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    
    private RoleMapper roleMapper;
    
    /**
     * 角色信息-列表
     *
     * @param role 查询条件
     *
     * @return 分页列表
     */
    @Override
    public IPage<Role> pageRole(Role role) {
        return page(PageUtil.getPlusPage(role), Wrappers.lambdaQuery(role).select(Role::getRoleId));
    }
    
    /**
     * 根据id获取角色信息表详情
     *
     * @param id id
     *
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
     * @param ids ids
     */
    @Override
    public void deleteRole(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }
    
    @Async
    public void ssx() throws Exception {
        Thread.sleep(1000);
        log.info("lailelaile");
    }
    
}
