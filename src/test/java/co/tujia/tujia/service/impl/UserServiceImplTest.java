package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.User;
import co.tujia.tujia.enums.Role;
import co.tujia.tujia.repository.UserRepository;
import co.tujia.tujia.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepo;

    @InjectMocks
    UserService userService;

    User user1 = new User();
    User user2 = new User();

    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user1.setId("USR001");
        user1.setName("User Name");
        user1.setPhone("+212 677889900");
        user1.setEmail("user_1@email.com");
        user1.setPassword("aPassword");
        user1.setIsActive(true);
        user1.setRole(Role.USER);

        user2.setId("USR002");
        user2.setName("User Name");
        user2.setPhone("+212 677889900");
        user2.setEmail("user_2@email.com");
        user2.setPassword("aPassword");
        user2.setIsActive(true);
        user2.setRole(Role.USER);

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void save() {
        Mockito.lenient().when(userRepo.save(user1)).thenReturn(user1);
        User user = userRepo.save(user1);
        assertThat(user).isEqualTo(user1);
    }

    @Test
    void findById() {
        Mockito.lenient().when(userRepo.findById(user1.getId())).thenReturn(Optional.of(user1));
        Optional<User> user = userService.findById(user1.getId());
        assertThat(user).isEqualTo(user1);
    }

    @Test
    void findByEmail() {
        Mockito.lenient().when(userRepo.findByEmail(user1.getEmail())).thenReturn(user1);
        Optional<User> user = userService.findByEmail(user1.getEmail());
        assertThat(user).isEqualTo(user1);
    }

    @Test
    void findAll() {
        given(userRepo.findAll()).willReturn(userList);
        List<User> users = userService.findAll();
        assertEquals(userList, users);
        verify(userRepo).findAll();
    }

    @Test
    void deleteById() {
        Mockito.lenient().when(userRepo.findById(user1.getId())).thenReturn(Optional.of(user1));
        userService.deleteById(user1.getId());
    }
}