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
        //TODO: determine remote server
        String url =  "http://example.com/";
        virtualServer.expect(requestTo(url)).andRespond(withSuccess(
                objectMapper.writeValueAsString(Object),
                MediaType.APPLICATION_JSON));

        //TODO: perform request
        MvcResult mvcResult = mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk()).andReturn();

        //TODO: check response
        String response = mvcResult.getResponse().getContentAsString();
    }
}
