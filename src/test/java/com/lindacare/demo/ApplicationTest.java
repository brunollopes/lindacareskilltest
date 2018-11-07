package com.lindacare.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lindacare.demo.dto.ResponseDto;
import com.lindacare.demo.dto.TradeDto;
import com.lindacare.demo.model.TradeMessages;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"com.lindacare.demo"})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, clazz);
    }
    List<TradeMessages> data;
    long time;

    @Test
    @Before
    public void createProduct() throws Exception {
        String uri = "/api/trade";
        TradeDto product = new TradeDto();
        product.setAmountSell(100d);
        product.setAmountBuy(100d);
        product.setRate(100d);
        product.setCurrencyFrom("NPR");
        product.setCurrencyTo("INR");
        product.setOriginatingCountry("NP");
        product.setTimePlaced(new Date());
        product.setUserId(12345);

        String inputJson = mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        ResponseDto create = mapFromJson(content, ResponseDto.class);
        Assert.assertTrue(create.isStatus());
    }

    @Test
    public void getProductsList() throws Exception {
        String uri = "/api/trade";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        ResponseDto productlist = mapFromJson(content, ResponseDto.class);
        Assert.assertTrue(productlist.isStatus());
        Assert.assertTrue(productlist.getData() instanceof List);
        this.data = (List<TradeMessages>) productlist.getData();
        this.time = productlist.getTimestamp();
    }

}
