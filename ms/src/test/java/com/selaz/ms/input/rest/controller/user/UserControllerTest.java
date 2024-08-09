package com.selaz.ms.input.rest.controller.user;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
import com.selaz.ms.input.rest.controller.user.dto.CreateUserRequestDTO;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;
import com.selaz.ms.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {
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
    public void testCreate() throws Exception {
        // UserJpaModel user =
        // UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username").tasks(new
        // ArrayList<>()).build();
        // this.repository.save(user);

        CreateUserRequestDTO dto = new CreateUserRequestDTO("test123", Nivel.USER);

        mvc.perform(MockMvcRequestBuilders.post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateError() throws Exception {
        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("test1234")
                .tasks(new ArrayList<>()).build();
        this.repository.save(user);

        AuthRequestDTO dto = new AuthRequestDTO("test1234");

        mvc.perform(MockMvcRequestBuilders.post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void testDeleteUser() throws Exception {
        UserJpaModel user = UserJpaModel.builder().nivel(Nivel.ADMIN).username("delete_user1").tasks(new ArrayList<>())
                .build();
        var response = this.repository.save(user);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/" + response.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", TestUtils.generateToken(user.getUsername())))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Should be able to get user by id")
    public void testGetUserById() throws Exception {
        UserJpaModel user = UserJpaModel.builder().nivel(Nivel.ADMIN).username("delete_user1").tasks(new ArrayList<>())
                .build();
        var response = this.repository.save(user);

        var token = TestUtils.generateToken(response.getUsername());

        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.get("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
