package com.selaz.ms.repository.user.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.selaz.ms.repository.user.jpa.model.UserJpaModel;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaModel, Long>{
    Optional<UserJpaModel> findByUsername(String username);
}