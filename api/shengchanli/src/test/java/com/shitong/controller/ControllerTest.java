package com.shitong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;

/** 
* @author  半天  
* @version 创建时间：2018年3月16日 上午9:48:16  
 * @param <LoginService>
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

}
