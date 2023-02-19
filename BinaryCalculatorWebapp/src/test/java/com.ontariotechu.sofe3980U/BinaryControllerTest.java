package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCalculatorPage() throws Exception {
        // MockMvc is used to simulate a GET request to "/"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // Verify that HTTP status is OK (200)
                .andExpect(view().name("calculator")) // Verify that the view is "calculator"
                .andExpect(model().attribute("operand1", "")); // Verify that the initial value of "operand1" is empty
    }
    @Test
    public void testAddition() throws Exception {
        // MockMvc is used to simulate a POST request to "/"
        mockMvc.perform(post("/")
                        .param("operand1", "101")
                        .param("operator", "+")
                        .param("operand2", "110"))
                .andExpect(status().isOk())  // Verify that HTTP status is OK (200)
                .andExpect(view().name("result")) // Verify that the view is "result"
                .andExpect(model().attribute("operand1", "101")) // Verify that the "operand1" value is set to "101"
                .andExpect(model().attribute("operator", "+")) // Verify that the "operator" value is set to "+"
                .andExpect(model().attribute("operand2", "110")) // Verify that the "operand2" value is set to "110"
                .andExpect(model().attribute("result", "1011")); // Verify that the "result" value is set to "1011"
    }
    @Test
    public void testInvalidOperator() throws Exception {
        // MockMvc is used to simulate a POST request to "/"
        mockMvc.perform(post("/")
                        .param("operand1", "101")
                        .param("operator", "-")
                        .param("operand2", "110"))
                .andExpect(status().isOk())  // Verify that HTTP status is OK (200)
                .andExpect(view().name("Error")); // Verify that the view is "Error"
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }

    @Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

}