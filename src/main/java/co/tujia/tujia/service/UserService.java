package co.tujia.tujia.service;

import co.tujia.tujia.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> update(String id, User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void deleteById(String id);

    boolean checkEmail(String email);

    void changePassword(String password);
}
