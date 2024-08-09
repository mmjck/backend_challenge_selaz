package com.selaz.ms.input.rest.controller.auth;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.selaz.ms.core.domain.user.entity.Nivel;
import com.selaz.ms.input.rest.controller.auth.dto.AuthRequestDTO;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;
import com.selaz.ms.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private UserJpaRepository repository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testLoginError() throws Exception {

        AuthRequestDTO dto = new AuthRequestDTO("random");

         mvc.perform(MockMvcRequestBuilders.post("/api/token/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void testLogin() throws Exception {
        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username").tasks(new ArrayList<>()).build();
        this.repository.save(user);

        AuthRequestDTO dto = new AuthRequestDTO("username");

         mvc.perform(MockMvcRequestBuilders.post("/api/token/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
