package com.selaz.ms.repository.user.jpa.mapper;



import java.util.function.Function;

import com.selaz.ms.core.domain.user.entity.User;
import com.selaz.ms.repository.user.jpa.model.UserJpaModel;

public class UserJpaModel2UserMapper implements Function<UserJpaModel, User> {

    public static User mapper(final UserJpaModel model) {
        return new UserJpaModel2UserMapper().apply(model);
    }

    @Override
    public User apply(UserJpaModel model) {
        return User
                .builder()
                .id(model.getId())
                .nivel(model.getNivel())
                .username(model.getUsername())
                .build();

    }

}
