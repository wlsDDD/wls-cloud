package plus.wls.common.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import plus.wls.common.core.constant.SuppressWarningConstants;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树-工具类
 *
 * @author wls
 * @since 2021/09/29 14:52:15
 */
public class TreeUtil {
    
    /**
     * 自定义树相关属性配置
     * 树属性配置参考  {@link TreeNodeConfig}
     */
    public static final TreeNodeConfig TREE_CONFIG;
    
    static {
        TREE_CONFIG = new TreeNodeConfig();
        TREE_CONFIG.setIdKey("id");
        TREE_CONFIG.setParentIdKey("parentId");
        TREE_CONFIG.setWeightKey("createTime");
    }
    
    /**
     * 列表转树
     *
     * @param list 列表
     *
     * @return {@link List} 树列表
     *
     * @author wls
     */
    public static <T> List<Tree<Long>> toTree(List<T> list) {
        return toTree(list, TREE_CONFIG);
    }
    
    /**
     * 列表转树
     *
     * @param list           列表
     * @param treeNodeConfig 转换配置
     *
     * @return {@link List} 树列表
     *
     * @author wls
     */
    public static <T> List<Tree<Long>> toTree(List<T> list, TreeNodeConfig treeNodeConfig) {
        return cn.hutool.core.lang.tree.TreeUtil.build(list, 0L, treeNodeConfig, (treeNode, tree) -> tree.putAll(BeanUtil.beanToMap(treeNode)));
    }
    
    /**
     * 寻找无上级节点,并将其转换为根节点
     *
     * @param list           列表
     * @param treeNodeConfig 转换配置
     *
     * @return 列表
     *
     * @author wls
     */
    @SuppressWarnings(SuppressWarningConstants.UNCHECKED)
    public static <T> List<T> convertRootNode(List<T> list, TreeNodeConfig treeNodeConfig) {
        return list.parallelStream().map(item -> {
            Map<String, Object> map = BeanUtil.beanToMap(item);
            if (list.parallelStream().noneMatch(
                    value -> BeanUtil.beanToMap(value).get(treeNodeConfig.getIdKey())
                                     .equals(map.get(treeNodeConfig.getParentIdKey())))) {
                map.put(treeNodeConfig.getParentIdKey(), 0L);
                return (T) BeanUtil.toBean(map, item.getClass());
            }
            return item;
        }).collect(Collectors.toList());
    }
    
}
