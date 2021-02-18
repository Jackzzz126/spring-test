package org.jack.rock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserTest
 *
 * @author zhengzhe17
 * @date 2020/7/14
 */
//@WebMvcTest(UserRest.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void regTest() throws Exception {
        //MvcResult mvcResult = mockMvc.perform(
        mockMvc.perform(
                post("/user/reg")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("{\"name\": \"jack\", \"pwd\": \"123\"}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("code").value(0))
                .andExpect(jsonPath("msg").exists())
                .andExpect(jsonPath("msg").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}


