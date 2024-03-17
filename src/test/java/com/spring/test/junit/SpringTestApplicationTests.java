package com.spring.test.junit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class SpringTestApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockRestServiceServer virtualServer;

    @Test
    void test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        assertEquals("{\"body\":\"body\"}", response);
    }

    @Test
    void test1() throws Exception {
        String url =  "http://example.com1/";
        virtualServer.expect(requestTo(url)).andRespond(withSuccess(
                objectMapper.writeValueAsString(new ServiceWithRestTemplate.ResponseModel("body1")),
                MediaType.APPLICATION_JSON));

        MvcResult mvcResult = mockMvc.perform(get("/api/test1"))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        assertEquals("{\"body\":\"body1\"}", response);
    }
}
