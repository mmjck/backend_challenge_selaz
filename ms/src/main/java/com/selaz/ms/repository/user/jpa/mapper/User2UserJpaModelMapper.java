package com.selaz.ms.repository.user.jpa.mapper;


import java.util.function.Function;

import com.selaz.ms.core.domain.user.entity.User;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;

public class User2UserJpaModelMapper implements Function<User, UserJpaModel> {

    public static UserJpaModel mapper(final User model) {
        return new User2UserJpaModelMapper().apply(model);
    }

    @Override
    public UserJpaModel apply(User model) {
        return UserJpaModel
                .builder()
                .id(model.getId())
                .nivel(model.getNivel())
                .username(model.getUsername())
                .build();

    }

}
