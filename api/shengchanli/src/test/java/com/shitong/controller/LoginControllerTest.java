package com.shitong.controller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shitong.entity.User;

import org.hamcrest.Matchers;
import org.junit.After;


/** 
* @author  半天  
* @version 创建时间：2018年3月16日 上午9:55:39  
*/
public class LoginControllerTest extends ControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    @Autowired  
    private WebApplicationContext wac; 
    
    @Before
    public void before() throws Exception {
    	logger.info("before()");  
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    	//单个类  拦截器无效

    	// mockMvc = MockMvcBuilders.standaloneSteup(userController).build(); 
    }  
    
    @Test
    public void test() throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "zhangsan").param("password", "123456")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
                //.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
        
    }
    
    @After
    public void after() throws Exception {

    }
}
