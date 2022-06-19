package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.User;
import co.tujia.tujia.enums.Role;
import co.tujia.tujia.repository.UserRepository;
import co.tujia.tujia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User userRequest) {

        String encryptedPassword = this.bCryptPasswordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encryptedPassword);
        userRequest.setIsActive(true);

        if (userRequest.getRole() == null)
            userRequest.setRole(Role.USER);

        return userRepo.save(userRequest);
    }

    @Override
    public Optional<User> update(String id, User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepo.findByEmail(email));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

    @Override
    public void changePassword(String password) {

    }

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        User user = userRepo.findByEmail(username);
        if (username != null)
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
        else
            throw new UsernameNotFoundException("The user with the following email : " + user.getEmail() + " is not found!");
    }
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userRepo.findByEmail(username);
            return UserDetailsImpl.build(user);
        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("The user with the following email : " + username + " is not found!");
        }
    }
}
