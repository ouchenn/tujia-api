package co.tujia.tujia.controller;

import co.tujia.tujia.domain.User;
import co.tujia.tujia.enums.Role;
import co.tujia.tujia.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    String url = "/api/user";
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
    void getAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get(url + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        when(userService.findById(user1.getId())).thenReturn(java.util.Optional.ofNullable(user1));

        mockMvc.perform(get(url + user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        when(userService.save(user1)).thenReturn(user1);

        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(user1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteById(user1.getId());

        mockMvc.perform(delete(url + user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}