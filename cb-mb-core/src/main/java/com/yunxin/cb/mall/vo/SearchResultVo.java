package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.vo.search.CommodityVO;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.Map;

@ApiModel(value="搜索返回对象",description="搜索返回对象 SearchResultVo")
public class SearchResultVo {

    private PageFinder<CommodityVO> pageFinder;

    public PageFinder<CommodityVO> getPageFinder() {
        return pageFinder;
    }

    public void setPageFinder(PageFinder<CommodityVO> pageFinder) {
        this.pageFinder = pageFinder;
    }

}
