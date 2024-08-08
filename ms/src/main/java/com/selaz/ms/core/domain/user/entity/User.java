package com.selaz.ms.core.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private Nivel nivel;
}
