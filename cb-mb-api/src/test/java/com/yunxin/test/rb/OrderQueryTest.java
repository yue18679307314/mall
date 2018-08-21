package com.yunxin.test.rb;

import com.yunxin.cb.Application;
import com.yunxin.cb.mall.service.ReimbursementQueryService;
import com.yunxin.cb.rest.mall.OrderResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class OrderQueryTest {
    private static final Logger log = LoggerFactory.getLogger(OrderQueryTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private ReimbursementQueryService reimbursementQueryService;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new OrderResource()).build();
//        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }


    @Test
    public void getOrderListTest() throws Exception {
        log.info("查询订单列表 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/order/pageList")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询订单列表 V1 end result : " + content);
    }
}
