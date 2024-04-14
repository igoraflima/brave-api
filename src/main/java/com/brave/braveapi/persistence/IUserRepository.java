package com.brave.braveapi.persistence;

import com.brave.braveapi.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    Optional<User> findOneByLoginAndPassword(String login, String password);
}
