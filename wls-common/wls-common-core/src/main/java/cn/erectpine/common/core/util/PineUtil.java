package cn.erectpine.common.core.util;

import cn.erectpine.common.core.function.FunctionSerializable;
import cn.erectpine.common.core.pojo.PinePage;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 核心工具类
 */
public class PineUtil {
    
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
     * 去除对象中所有String类型的前后空白
     *
     * @param coll coll
     */
    public static void trimBean(Collection<?> coll) {
        coll.forEach(PineUtil::trimBean);
    }
    
    /**
     * 去除对象中所有String类型的前后空白
     *
     * @param bean bean
     */
    public static void trimBean(Object bean) {
        BeanUtil.getBeanDesc(bean.getClass()).getProps().forEach(propDesc -> {
            if (String.class.getName().equals(propDesc.getFieldType().getTypeName())) {
                Object str = ReflectUtil.invoke(bean, propDesc.getGetter());
                if (str == null) {
                    return;
                }
                ReflectUtil.invoke(bean, propDesc.getSetter(), str.toString().trim());
            }
        });
    }
    
    /**
     * 自定义进制转换
     *
     * @param seq    对应规则的值
     * @param rexStr 规则
     * @return {@link String}
     */
    public static String getSeq(Integer seq, String rexStr) {
        int flag = seq;
        StringBuilder result = new StringBuilder();
        while (true) {
            result.insert(0, rexStr.charAt(seq % rexStr.length()));
            seq = seq / rexStr.length();
            if (seq / rexStr.length() == 0) {
                return flag < rexStr.length() ? result.toString() : rexStr.charAt(seq - 1) + result.toString();
            }
        }
    }
    
    
    /**
     * 根据类获取指定所有注解
     *
     * @param clazz 类clazz
     * @param ann   注解clazz
     * @return {@link List<>}
     */
    public static <T extends Annotation> List<T> getFieldAnnotations(Class<?> clazz, Class<T> ann) {
        return BeanUtil.getBeanDesc(clazz).getProps().stream()
                       .map(propDesc -> propDesc.getField().getAnnotation(ann))
                       .filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    /**
     * 分页
     */
    public static void pageStart() {
        Integer pageNum = ServletUtil.getParameterToInt("pageNo", 1);
        Integer pageSize = ServletUtil.getParameterToInt("pageSize", 20);
        pageStart(pageNum, pageSize);
    }
    
    /**
     * 分页
     *
     * @param pinePage 我的页面
     */
    public static void pageStart(PinePage pinePage) {
        pageStart(pinePage.getPageNum(), pinePage.getPageSize());
    }
    
    /**
     * 封装PageHelper分页
     * 使其支持 pageSize=0 时返回全部数据
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     */
    public static void pageStart(int pageNum, int pageSize) {
        // 为了兼容以前的-1查询全部
        if (pageNum == -1) {
            pageNum = 0;
        }
        PageHelper.startPage(pageNum, pageSize, true, null, true);
    }
    
    /**
     * 构建自定义分页对象
     *
     * @param list 列表
     * @return 自定义分页对象
     */
    public static <T> PinePage page(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return page(pageInfo);
    }
    
    /**
     * 构建自定义分页对象
     *
     * @param pageInfo {@link PageInfo}
     * @return 自定义分页对象
     */
    public static <T> PinePage page(PageInfo<T> pageInfo) {
        PinePage page = new PinePage();
        page.setTotalNum(pageInfo.getTotal())
            .setPageNum(pageInfo.getPageNum())
            .setPageSize(pageInfo.getPageSize())
            .setList(pageInfo.getList());
        return page;
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
     * 判断是否存在空集合
     *
     * @param collections 集合
     * @return true-存在空集合 false-不存在空集合，即全部集合为非空
     */
    public static boolean isNotAllEmpty(Collection<?>... collections) {
        for (Collection<?> collection : collections) {
            if (CollUtil.isEmpty(collection)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 从字符串中获取数字数字
     *
     * @param str str
     * @return {@link Integer}
     */
    public static Integer getNumeric(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Integer.valueOf(m.replaceAll("").trim());
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
     * 获取两个时间间的所有月份
     *
     * @param start 起始时间 格式 yyyy-MM
     * @param end   结束时间 格式 yyyy-MM
     * @return 月份集合
     */
    public static List<String> getDates(String start, String end) {
        return getDates(convertLocalDate(start), convertLocalDate(end));
    }
    
    /**
     * 获取两个时间间的所有月份
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return 月份集合
     */
    public static List<String> getDates(LocalDate startDate, LocalDate endDate) {
        List<String> dates = new LinkedList<>();
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            dates.add(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
            startDate = startDate.plusMonths(1);
        }
        return dates;
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
