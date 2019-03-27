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
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
    private ChatRepository repository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deveSalvar() throws Exception {
        User user = User.builder().id(100).build();
        Chat chat = Chat.builder().id(100).text("First Message").user(user).build();

        mockMvc.perform(post("/api/chat")
                .content(jacksonConverter.toJson(chat))
                .contentType(Request.getContentType()))
                .andExpect(status().isOk());

        List<Chat> cartoes = (List<Chat>) repository.findAll();

        assertEquals(1, cartoes.size());
    }
}
