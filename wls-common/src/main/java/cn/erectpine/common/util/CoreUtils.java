package cn.erectpine.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 核心工具类
 *
 * @Author wls
 * @Date 2021/1/12 11:58
 */
public class CoreUtils {
    
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
     * @return {@link List} 树列表
     * @Author wls
     */
    public static <T> List<Tree<Long>> toTree(List<T> list) {
        return TreeUtil.build(list, 0L, TREE_CONFIG, (treeNode, tree) -> tree.putAll(BeanUtil.beanToMap(treeNode)));
    }
    
    /**
     * 列表转树
     *
     * @param list           列表
     * @param treeNodeConfig 转换配置
     * @return {@link List} 树列表
     * @Author wls
     */
    public static <T> List<Tree<Long>> toTree(List<T> list, TreeNodeConfig treeNodeConfig) {
        return TreeUtil.build(list, 0L, treeNodeConfig, (treeNode, tree) -> tree.putAll(BeanUtil.beanToMap(treeNode)));
    }
    
    /**
     * 寻找无上级节点,并将其转换为根节点
     *
     * @param list           列表
     * @param treeNodeConfig 转换配置
     * @return 列表
     * @Author wls
     */
    public static <T> List<T> convertRootNode(List<T> list, TreeNodeConfig treeNodeConfig) {
        return list.parallelStream().map(e -> {
            Map<String, Object> map = BeanUtil.beanToMap(e);
            if (list.parallelStream().noneMatch(
                    value -> BeanUtil.beanToMap(value).get(treeNodeConfig.getIdKey())
                                     .equals(map.get(treeNodeConfig.getParentIdKey())))) {
                map.put(treeNodeConfig.getParentIdKey(), 0L);
                return (T) BeanUtil.toBean(map, e.getClass());
            }
            return e;
        }).collect(Collectors.toList());
    }
    
    
    /**
     * 浅拷贝-拷贝属性
     * 将old类中的属性拷贝到fresh中
     * 拷贝规则: 属性名相同
     *
     * @param old   源对象
     * @param fresh 拷贝后的对象
     * @return fresh
     * @Author wls
     */
    public static <T> T convertFor(Object old, T fresh) {
        BeanUtils.copyProperties(old, fresh);
        return fresh;
    }
    
    /**
     * 获取日期echarts
     *
     * @param days 时间段
     * @return {@link Map}
     */
    public static Map<String, Long> getDateEchartsMap(Integer days) {
        return getDateEchartsMap(LocalDateTime.now(), days);
    }
    
    /**
     * 获取日期echarts
     *
     * @param now  当前时间
     * @param days 时间段
     * @return {@link Map}
     */
    public static Map<String, Long> getDateEchartsMap(LocalDateTime now, Integer days) {
        Map<String, Long> map = new TreeMap<>();
        for (int i = 0; i < days; i++) {
            LocalDateTime offset = LocalDateTimeUtil.offset(now, -i, ChronoUnit.DAYS);
            String format = LocalDateTimeUtil.format(offset, DatePattern.NORM_DATE_PATTERN);
            map.put(format, 0L);
        }
        return map;
    }
    
}
