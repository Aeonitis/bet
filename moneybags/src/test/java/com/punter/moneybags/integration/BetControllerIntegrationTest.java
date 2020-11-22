package com.punter.moneybags.integration;

import com.punter.moneybags.controller.BetController;
import com.punter.moneybags.service.BetRepository;
import com.punter.moneybags.service.BetService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class BetControllerIntegrationTest {

  @MockBean
  private BetRepository betRepository;

  @MockBean
  BetController betController;

  @MockBean
  BetService betService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenValidBetRequestSentThen200() throws Exception {
    final String validJSONRequest = "[{\n"
        + "   \"betId\": \"Bet-99\",\n"
        + "   \"betTimestamp\": 1489490156000,\n"
        + "   \"selectionId\": 8888,\n"
        + "   \"selectionName\": \" Valid Name\",\n"
        + "   \"stake\": 42.42,\n"
        + "   \"price\": 100.01,\n"
        + "   \"currency\": \" USD\"\n"
        + " }]";
    mockMvc.perform(MockMvcRequestBuilders.post("/post-json")
        .content(validJSONRequest)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void whenInvalidBetRequestSentThen400() throws Exception {
    String invalidJSONRequest = "{\n"
        + "   \"betId\": \"Bet-99\",\n"
        + "   \"betTimestamp\": 1489490156000,\n"
        + "   \"selectionId\": 8888,\n"
        + "   \"selectionName\": \" Valid Name\",\n"
        + "   \"stake\": 42.42,\n"
        + "   \"price\": 100.01,\n"
        + "   \"currency\": \" USD\"\n"
        + " }";

    mockMvc.perform(MockMvcRequestBuilders.post("/post-json")
        .content(invalidJSONRequest)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

  }

  @Test
  public void whenNonCSVPostToCSVEndpointThen400() throws Exception {
    final String CSV_ENDPOINT = "/post-csv";
    final String validJSONButInvalidCSVRequest = "[{\n"
        + "   \"betId\": \"Bet-valid-wrong-endpoint\",\n"
        + "   \"betTimestamp\": 1489490156000,\n"
        + "   \"selectionId\": 8888,\n"
        + "   \"selectionName\": \" Valid Name\",\n"
        + "   \"stake\": 42.42,\n"
        + "   \"price\": 100.01,\n"
        + "   \"currency\": \" USD\"\n"
        + " }]";

    mockMvc.perform(MockMvcRequestBuilders.post(CSV_ENDPOINT)
        .content(validJSONButInvalidCSVRequest)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers
            .jsonPath("$.Message", Is.is("Current request is not a multipart request")))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.Advice", Is.is("Please post a valid CSV file")))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}