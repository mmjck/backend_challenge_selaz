package com.selaz.ms.service.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.selaz.ms.core.domain.user.entity.Nivel;
import com.selaz.ms.core.domain.user.gateway.UserGateway;
import com.selaz.ms.repository.task.jpa.TaskJpaRepository;
import com.selaz.ms.repository.user.UserJpaGateway;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;
import com.selaz.ms.service.user.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private TaskJpaRepository taskJpaRepository;

    @Mock
    private UserJpaRepository repository;

    @Mock
    private UserGateway gateway;

    @Mock
    private UserService userService;

    @Mock
    private UserJpaGateway userJpaGateway;

    @InjectMocks
    private UserServiceImpl service;


    
    // @BeforeEach
    // void setup() {
    //     MockitoAnnotations.openMocks(this);
    // }

    @Test
    void testCreate() {
        UserJpaModel user = UserJpaModel.builder().id(Long.valueOf(1)).nivel(Nivel.ADMIN).username("username").build();
        when(this.repository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(this.repository.save(any(UserJpaModel.class))).thenReturn(user);

        var response =  this.service.create("username", Nivel.ADMIN);

        assertNotNull(response);
        verify(repository, times(1)).save(any(UserJpaModel.class));
    }
}
