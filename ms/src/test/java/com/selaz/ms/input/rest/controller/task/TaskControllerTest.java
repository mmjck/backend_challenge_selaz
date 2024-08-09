package com.selaz.ms.input.rest.controller.task;

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
import com.selaz.ms.input.rest.controller.task.dto.CreateTaskRequestDTO;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;
import com.selaz.ms.utils.TestUtils;

import java.util.ArrayList;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TaskControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserJpaRepository userRepository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testCreate() throws Exception {
        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username_task")
                .tasks(new ArrayList<>()).build();

        this.userRepository.save(user);

        var token = "Bearer " + TestUtils.generateToken(user.getUsername());
        var dto = new CreateTaskRequestDTO("title", "description", LocalDateTime.of(2024, 12, 12, 3, 2, 1));

        mvc.perform(MockMvcRequestBuilders.post("/api/task/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testDelete() throws Exception {

        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username_task")
                .tasks(new ArrayList<>()).build();

        this.userRepository.save(user);

        var token = "Bearer " + TestUtils.generateToken(user.getUsername());
        var dto = new CreateTaskRequestDTO("title", "description", LocalDateTime.of(2024, 12, 12, 3, 2, 1));

        mvc.perform(MockMvcRequestBuilders.post("/api/task/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.delete("/api/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testListAll() throws Exception {

        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username_task")
                .tasks(new ArrayList<>()).build();

        this.userRepository.save(user);

        var token = "Bearer " + TestUtils.generateToken(user.getUsername());
        var dto = new CreateTaskRequestDTO("title", "description", LocalDateTime.of(2024, 12, 12, 3, 2, 1));

        mvc.perform(MockMvcRequestBuilders.post("/api/task/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.post("/api/task/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.get("/api/task/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
