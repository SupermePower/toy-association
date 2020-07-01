package cn.toy.www.helper;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @description 获取分页对象
 * @author: Zero
 * @date: 2020/5/1 10:56
 */
@Component
public class PageInfoHelper<T> {

    /**
     * 获取分页对象
     *
     * @return 分页数据
     */
    public PageInfo<T> getPageInfo(Page search) {
        long total = search.getTotalElements();
        int pageSize = search.getSize();
        int pageNum = search.getNumber();
        PageInfo<T> pageInfo = new PageInfo<T>(search.getContent());
        pageInfo.setTotal(total);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        long totalPage = (total % pageSize) != 0 ? (total / Long.parseLong(pageSize + "")) + 1 : (total / Long.parseLong(pageSize + ""));
        pageInfo.setHasNextPage(totalPage > pageNum);
        return pageInfo;
    }
}
