package com.specification.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.specification.controller.UserController;
import com.specification.entities.Role;
import com.specification.entities.User;
import com.specification.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    Role mockAdminRole = new Role(2, "admin");
    User mockUser = new User(1, "suraj@gmail.com", "Suraj Verma", 28, mockAdminRole, null);

    @Test
    public void test_addUser() throws Exception {
        when(userService.create(mockUser)).thenReturn(mockUser);
        mockMvc.perform(
                        post("/user/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(mockUser)))
                .andExpect(status().isOk());
    }


    @Test
    public void test_getUser() throws Exception {
        when(userService.getUser(mockUser.getId())).thenReturn(mockUser);
        mockMvc.perform(
                        get("/user/{id}", mockUser.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Suraj Verma"))
                .andExpect(jsonPath("$.role.type").value("admin"));
    }

    @Test
    public void test_findAllUsers() throws Exception {
        Role mockUserRole = new Role(1, "user");
        User mockUser1 = new User(2, "shyam@gmail.com", "Shyam Verma", 28, mockUserRole, null);
        List<User> users = List.of(mockUser, mockUser1);

        when(userService.getUsers()).thenReturn(users);
        mockMvc.perform(
                        get("/user/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Suraj Verma"))
                .andExpect(jsonPath("$[0].role.type").value("admin"))
                .andExpect(jsonPath("$[1].name").value("Shyam Verma"))
                .andExpect(jsonPath("$[1].role.type").value("user"));
    }

    @Test
    public void test_deleteUser() throws Exception {
        doNothing().when(userService).delete(mockUser);
        mockMvc.perform(
                        delete("/user/{id}", mockUser.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted"));
    }

    @Test
    public void test_updateUser() throws Exception {
        User inputUser = new User(0, null, null, 30, null, null);
        User updatedMockUser = new User(1, "suraj@gmail.com", "Suraj Verma", 30, mockAdminRole, null);
        when(userService.updateUser(mockUser.getId(), inputUser)).thenReturn(updatedMockUser);
        mockMvc.perform(
                        put("/user/{id}", mockUser.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(inputUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Suraj Verma"))
                .andExpect(jsonPath("$.age").value(30));
    }

}
