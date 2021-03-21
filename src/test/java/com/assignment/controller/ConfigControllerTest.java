package com.assignment.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getConfigByAppCodeAndVersion() throws Exception {
        //get existing conf
        String url = "/api/APP01/config/1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        //get non-existing conf
        String url2 = "/api/APP01/config/7";

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.get(url2)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getConfigByAppCode() throws Exception  {
        //get existing conf
        String url = "/api/APP01/config";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        //get non-existing conf
        String url2 = "/api/APP08/config";

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.get(url2)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addOrUpdateConfig() throws Exception  {
        //Add
        String url = "/api/APP06/config/1";
        String jsonConf = "{\"autoStart\":\"false\",\"Cron\":\"Monthly\",\"Notice\":\"Email\"}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConf)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        //Update without required header
        String url2 = "/api/APP01/config/1";

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post(url2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConf)
                .header("Origin","*"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print())
                .andReturn();

        //Update with required header of wrong value
        String url3 = "/api/APP01/config/1";

        MvcResult mvcResult3 = mockMvc.perform(MockMvcRequestBuilders.post(url3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConf)
                .header("Origin","*")
                .header("If-Match","somecode"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print())
                .andReturn();

        //Update with correct required header
        String url4 = "/api/APP01/config/1";
        String jsonConf4 = "{\"name\":\"NewApp\",\"host\":\"www.two.com\"}";
        MvcResult mvcResult4 = mockMvc.perform(MockMvcRequestBuilders.post(url4)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConf4)
                .header("Origin","*")
                .header("If-Match","885a45efff249a0321eac3c9ed25016f"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

}