package plus.wls.common.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 项目工具类
 *
 * @author wls
 * @since 2021/4/4 18:15
 */
public class FixUtil {
    
    /**
     * 获取两个时间间的所有月份
     *
     * @param start 起始时间 格式 yyyy-MM
     * @param end   结束时间 格式 yyyy-MM
     *
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
     *
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
     * 转换 "yyyy-MM" 为 LocalDate
     *
     * @param yearMonth 年月 yyyy-MM
     *
     * @return {@link LocalDate}
     */
    public static LocalDate convertLocalDate(String yearMonth) {
        return LocalDateTimeUtil.parseDate(yearMonth, DatePattern.NORM_MONTH_PATTERN);
    }
    
    /**
     * 获取日期echarts
     *
     * @param days 时间段
     *
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
     *
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
    
    /**
     * 判断是否存在空集合
     *
     * @param collections 集合
     *
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
    
}
