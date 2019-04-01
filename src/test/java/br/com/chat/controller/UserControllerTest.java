package br.com.chat.controller;

import br.com.chat.model.User;
import br.com.chat.util.JacksonConverter;
import br.com.chat.util.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private JacksonConverter jacksonConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void mustSave() throws Exception {
        User user = User.builder().id(101).build();

        mockMvc.perform(post("/user")
                .content(jacksonConverter.toJson(user))
                .contentType(Request.getContentType()))
                .andExpect(status().isOk());
    }
}
