package com.selaz.ms.infra.config.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.selaz.ms.core.exceptions.user.UserNotFoundException;
import com.selaz.ms.repository.user.jpa.UserJpaRepository;
import com.selaz.ms.repository.user.jpa.mapper.UserJpaModel2UserMapper;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    private UserJpaRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserJpaRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            UserJpaModel user = userRepository.findByUsername(login).orElseThrow(() -> new UserNotFoundException());

            var authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getNivel().getMessate()));
            var authentication = new UsernamePasswordAuthenticationToken(UserJpaModel2UserMapper.mapper(user), null,
                    authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");

        if (header == null) {
            return null;
        }

        return header.replace("Bearer ", "");
    }
}
