package plus.wls.common.web.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import plus.wls.common.core.pojo.Page;

import java.util.List;

/**
 * 分页工具类
 *
 * @author wls
 * @since 2021/09/22 11:33:32
 */
public class PageUtil {
    
    /**
     * 获取mybatisPlus分页对象
     *
     * @param type 定义Page泛型
     *
     * @return {@link com.baomidou.mybatisplus.extension.plugins.pagination.Page}<{@link T}>
     */
    @SafeVarargs
    public static <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> getPlusPage(T... type) {
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>().setCurrent(ServletUtil.getParameterToInt("pageNum", 1))
                                                                                  .setSize(ServletUtil.getParameterToInt("pageSize", 20));
    }
    
    /**
     * 分页
     */
    public static void pageStart() {
        Integer pageNum = ServletUtil.getParameterToInt("pageNum", 1);
        Integer pageSize = ServletUtil.getParameterToInt("pageSize", 20);
        pageStart(pageNum, pageSize);
    }
    
    /**
     * 封装PageHelper分页
     * 使其支持 pageSize=0 时返回全部数据
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     */
    public static void pageStart(int pageNum, int pageSize) {
        // 为了兼容-1查询全部
        if (pageNum == -1) {
            pageNum = 0;
        }
        PageHelper.startPage(pageNum, pageSize, true, null, true);
    }
    
    /**
     * 分页
     *
     * @param page 我的页面
     */
    public static void pageStart(Page<?> page) {
        pageStart(page.getPageNum(), page.getPageSize());
    }
    
    /**
     * 构建自定义分页对象
     *
     * @param list 列表
     *
     * @return 自定义分页对象
     */
    public static <T> Page<T> page(List<T> list) {
        return page(new PageInfo<>(list));
    }
    
    /**
     * 构建自定义分页对象
     *
     * @param pageInfo {@link PageInfo}
     *
     * @return 自定义分页对象
     */
    public static <T> Page<T> page(PageInfo<T> pageInfo) {
        return new Page<T>().setPageNum(pageInfo.getPageNum())
                            .setPageSize(pageInfo.getPageSize())
                            .setTotalNum(pageInfo.getTotal())
                            .setList(pageInfo.getList());
    }
    
}
