package io.github.ricardoandradem.mgl.services;

import io.github.ricardoandradem.mgl.model.User;
import io.github.ricardoandradem.mgl.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User create(User userToCreate) {
        return repository.save(userToCreate);
    }
}
