package org.jack.rock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.jack.rock.rest.GreetingRest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * GreetingTest
 *
 * @author zhengzhe17
 * @date 2020/7/13
 */
@WebMvcTest(GreetingRest.class)
public class GreetingTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/greeting")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("name", "Jack")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("code").value(0))
                .andExpect(jsonPath("msg").exists())
                .andExpect(jsonPath("msg").value("success"))
                .andExpect(jsonPath("data.content").exists())
                .andExpect(jsonPath("data.content").value("Hello Jack"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}

