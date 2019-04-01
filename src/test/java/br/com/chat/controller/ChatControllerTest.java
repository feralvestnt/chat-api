package br.com.chat.controller;

import br.com.chat.model.Chat;
import br.com.chat.model.User;
import br.com.chat.repository.ChatRepository;
import br.com.chat.util.JacksonConverter;
import br.com.chat.util.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
@Sql({"/chat.sql"})
public class ChatControllerTest {

    @Autowired
    private JacksonConverter jacksonConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ChatRepository chatRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void mustReturnAll() throws Exception {
        mockMvc.perform(get("/chat"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(Request.getContentType()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].text", is("TEST MESSAGE")))
                .andExpect(jsonPath("$[0].user.name", is("Fernando Henrique Alves")))
                .andExpect(jsonPath("$[0].user.id", is(100)));

    }

    @Test
    public void deveSalvar() throws Exception {
        User user = User.builder().id(100).build();
        Chat chat = Chat.builder().id(null).text("First Message").user(user).build();

        mockMvc.perform(post("/chat")
                .content(jacksonConverter.toJson(chat))
                .contentType(Request.getContentType()))
                .andExpect(status().isOk());

        List<Chat> cartoes = (List<Chat>) chatRepository.findAll();

        assertEquals(2, cartoes.size());
    }
}
