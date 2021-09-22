package cn.erectpine.common.core.aspect;

import cn.erectpine.common.core.annotation.Page;
import cn.erectpine.common.core.util.pine.AspectUtil;
import cn.erectpine.common.core.util.pine.PageUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 解析处理分页注解
 *
 * @author wls
 * @since 2021/5/31 9:27
 */
@Slf4j
@Aspect
@Component
public class PageAnnotationAspect {
    
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(cn.erectpine.common.core.annotation.Page)")
    public void pointCut() {
    }
    
    /**
     * 分页
     */
    @Around("pointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        int pageNum = 0;
        int pageSize = 0;
        // 获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (ObjectUtil.contains(arg, pageNum) && ObjectUtil.contains(arg, pageSize)) {
                Map<String, Object> map = BeanUtil.beanToMap(arg);
                pageNum = (int) map.get("pageNum");
                pageSize = (int) map.get("pageSize");
                break;
            }
        }
        // 未获取到值使用默认值
        if (pageNum == 0 && pageSize == 0) {
            // 获取注解
            Page page = AspectUtil.getAnnotation(joinPoint, Page.class);
            Assert.notNull(page, "未获取到注解");
            pageNum = page.defaultPageNum();
            pageSize = page.defaultPageSize();
        }
        // 开启分页
        PageUtil.pageStart(pageNum, pageSize);
        // 执行方法
        Object proceed = joinPoint.proceed();
        // 封装返回结果
        if (proceed instanceof List<?>) {
            List<?> list = (List<?>) proceed;
            return PageUtil.page(list);
        }
        return proceed;
    }
    
}
