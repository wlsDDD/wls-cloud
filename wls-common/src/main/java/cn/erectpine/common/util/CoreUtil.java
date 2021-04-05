package cn.erectpine.common.util;

import cn.erectpine.common.function.FunctionSerializable;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
@Component
public class CoreUtil {
    
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
        return toTree(list, TREE_CONFIG);
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
    
    /**
     * 浅拷贝-拷贝属性
     * 将old类中的属性拷贝到fresh中
     * 拷贝规则: 属性名相同
     *
     * @param old     源对象
     * @param fresh   拷贝后的对象
     * @param ignores 忽略字段
     * @return fresh
     * @Author wls
     */
    @SafeVarargs
    public static <T> T copyBean(Object old, T fresh, FunctionSerializable<T, ?>... ignores) {
        BeanUtil.copyProperties(old, fresh, LamUtil.getFieldNames(ignores));
        return fresh;
    }
    
    /**
     * 得到简单的堆栈跟踪
     *
     * @param e       e
     * @param contain 包含字符串
     * @return {@link StackTraceElement[]}
     */
    public static StackTraceElement[] getSimpleStackTrace(Throwable e, String contain) {
        return Arrays.stream(e.getStackTrace()).distinct().parallel()
                     .filter(item -> item.getLineNumber() != -1 && item.getClassName().contains(contain))
                     .toArray(StackTraceElement[]::new);
    }
    
    /**
     * json 去除json文本中的转义字符
     *
     * @param text 文本
     * @return {@link String}
     */
    public static String jsonDelEscape(String text) {
        return text.replaceAll("\\\\", "")
                   .replaceAll("\"\\{", "{")
                   .replaceAll("\"\\[", "[")
                   .replaceAll("]\"", "]")
                   .replaceAll("}\"", "}");
    }
    
    /**
     * 转换 "yyyy-MM" 为 LocalDate
     *
     * @param yearMonth 年月 yyyy-MM
     * @return {@link LocalDate}
     */
    public static LocalDate convertLocalDate(String yearMonth) {
        return LocalDateTimeUtil.parseDate(yearMonth, DatePattern.NORM_MONTH_PATTERN);
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
