package cn.p2nn.meteor.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageResult<T> {

    private long current = 1;

    private long pageSize = 10;

    private long total = 0;

    private T record;

    private PageResult(){}

    public static PageResult parse(Page page) {
        PageResult result = new PageResult<>();
        result.setCurrent(page.getCurrent()).setPageSize(page.getSize()).setTotal(page.getTotal()).setRecord(page.getRecords());
        return result;
    }
}
