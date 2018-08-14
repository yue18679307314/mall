package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.HistoryRecord;
import com.yunxin.cb.mall.service.HistoryRecordService;
import com.yunxin.cb.mall.vo.HistoryRecordVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Api(description = "我的浏览接口")
@RestController
@RequestMapping(value = "/{version}/mall/history")
public class HistoryRecordResource extends BaseResource {

    @Resource
    private HistoryRecordService historyRecordService;

    @ApiOperation(value = "获取用户收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCustomerHistoryRecord")
    @ApiVersion(1)
    public ResponseResult<PageFinder<HistoryRecordVO>> getCustomerHistoryRecord(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        Query q = new Query(pageNo, pageSize);
        HistoryRecord historyRecord=new HistoryRecord();
        historyRecord.setCustomerId(getCustomerId());
        q.setData(historyRecord);
        PageFinder<HistoryRecord> pageFinder=historyRecordService.pageCustomerHistoryRecords(q);
        PageFinder<HistoryRecordVO> page=HistoryRecordVO.dOconvertVOPage(pageFinder);
        return new ResponseResult(page);
    }

    @ApiOperation(value = "商品添加收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "salePrice", value = "销售价", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "addHistoryRecord")
    @ApiVersion(1)
    public ResponseResult<HistoryRecordVO> addHistoryRecord(@RequestBody HistoryRecordVO HistoryRecordVO) {
        try {
            logger.info("input Parameter HistoryRecordVO:" + HistoryRecordVO.toString());
            HistoryRecord historyRecord = new HistoryRecord();
            BeanUtils.copyProperties(historyRecord, HistoryRecordVO);
            historyRecord.setCustomerId(getCustomerId());
            historyRecord = historyRecordService.addHistoryRecord(historyRecord);
            if (historyRecord != null) {
                BeanUtils.copyProperties(HistoryRecordVO, historyRecord);
                return new ResponseResult(HistoryRecordVO);//成功
            } else {
                return new ResponseResult(Result.FAILURE);//失败
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException is "+e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException is "+e);
        }
        return new ResponseResult(Result.SUCCESS);//成功
    }

    /**
     * @title: 商品移出收藏夹(批量)
     * @param: [HistoryRecordIds]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹(批量)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "HistoryRecordIds", value = "收藏夹id集合", required = true, paramType = "post", dataType = "list<int>")})
    @PostMapping(value = "delHistoryRecords")
    @ApiVersion(1)
    public ResponseResult delHistoryRecords(@RequestBody List<Integer> historyRecordIds){
        try {
            if(LogicUtils.isNullOrEmpty(historyRecordIds)){
                return new ResponseResult(Result.FAILURE,"参数为空");//失败
            }
            int result=historyRecordService.removeHistoryRecordBatch(historyRecordIds, getCustomerId());
            if(result>0){
                return new ResponseResult(Result.SUCCESS);//成功
            }else{
                return new ResponseResult(Result.FAILURE);//失败
            }
        }catch (Exception e){
            logger.error("Exception is "+e);
        }
        return new ResponseResult(Result.FAILURE);//失败
    }
}
