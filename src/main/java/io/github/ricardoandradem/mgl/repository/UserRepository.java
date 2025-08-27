package io.github.ricardoandradem.mgl.repository;

import io.github.ricardoandradem.mgl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
