package com.selaz.ms.input.rest.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selaz.ms.infra.config.security.TokenService;
import com.selaz.ms.input.rest.controller.auth.dto.AuthRequestDTO;
import com.selaz.ms.input.rest.controller.auth.dto.AuthResponseDTO;
import com.selaz.ms.service.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/token/")
public class AuthController implements AuthApi {

    private final UserService service;
    private final TokenService tokenService;

    public AuthController(UserService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }


    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        var response = this.service.findByUsername(request.username());

        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();    
        }

        return ResponseEntity.ok().body(new AuthResponseDTO(this.tokenService.generateToken(response)));
    }
    
    
}
