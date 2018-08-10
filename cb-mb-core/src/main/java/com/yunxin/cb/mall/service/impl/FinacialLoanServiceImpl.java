package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.mapper.FinacialLoanMapper;
import com.yunxin.cb.mall.service.FinacialLoanService;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.FinacialLoanVO;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinacialLoanServiceImpl implements FinacialLoanService {

    @Resource
    private FinacialLoanMapper finacialLoanMapper;
    @Resource
    private FinacialWalletService finacialWalletService;

    private static final Log log = LogFactory.getLog(FinacialLoanServiceImpl.class);
    /**
     * 添加
     * @author      likang
     * @param finacialLoanVO
     * @param finacialWalletVO
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:32
     */
    @Override
    @Transactional
    public FinacialLoanVO add(FinacialLoanVO finacialLoanVO, FinacialWalletVO finacialWalletVO){
        log.info("add:"+finacialLoanVO);
        FinacialLoan finacialLoan = new FinacialLoan();
        BeanUtils.copyProperties(finacialLoan, finacialLoanVO);
        //借款金额小于保单额度（优先减少保单额度，再减少感恩额度）
        if (finacialLoanVO.getAmount().compareTo(finacialWalletVO.getInsuranceAmount()) < 1) {
            finacialWalletVO.setInsuranceAmount(finacialWalletVO.getInsuranceAmount().subtract(finacialLoanVO.getAmount()));
        } else { //借款金额大于保单额度但小于总额度
            finacialWalletVO.setInsuranceAmount(BigDecimal.ZERO);
            finacialWalletVO.setCreditAmount(finacialLoanVO.getAmount().subtract(finacialWalletVO.getInsuranceAmount()));
        }
        //更新钱包额度
        finacialWalletService.updateFinacialWallet(finacialWalletVO);
        //添加额度明细
        finacialLoanMapper.insert(finacialLoan);
        return finacialLoanVO;
    }

    /**
     * 根据用户ID获取借款信息
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinacialLoanVO>
     * @exception
     * @date        2018/8/9 14:38
     */
    @Override
    public List<FinacialLoanVO> getByCustomerIdAndType(int customerId){
        List<FinacialLoan> list = finacialLoanMapper.selectByCustomerIdAndType(customerId);
        List<FinacialLoanVO> listVo = new ArrayList<>();
        list.stream().forEach(p ->{
            FinacialLoanVO vo = new FinacialLoanVO();
            BeanUtils.copyProperties(p, vo);
            listVo.add(vo);
        });
        return listVo;
    }

    /**
     * 获取一条记录
     * @author      likang
     * @param repaymentId
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinacialLoanVO getById(int repaymentId){
        FinacialLoanVO vo = new FinacialLoanVO();
        FinacialLoan loan = finacialLoanMapper.selectByPrimaryKey(repaymentId);
        BeanUtils.copyProperties(loan, vo);
        return vo;
    }

    /**
     * 更新
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinacialLoanVO update(FinacialLoanVO vo){
        log.info("update:"+vo);
        FinacialLoan finacialLoan = new FinacialLoan();
        BeanUtils.copyProperties(finacialLoan, vo);
        finacialLoanMapper.updateByPrimaryKey(finacialLoan);
        return vo;
    }

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    @Override
    public int countByCustomerId(int customerId) {
        Query q = new Query();
        FinacialLoan finacialLoan = new FinacialLoan();
        finacialLoan.setCustomerId(customerId);
        List<LoanState> list = new ArrayList<LoanState>();
        list.add(LoanState.APPLY_SUCCESS);
        list.add(LoanState.SETTLE);
        finacialLoan.setStateList(list);
        q.setData(finacialLoan);
        return finacialLoanMapper.count(q);
    }
}
