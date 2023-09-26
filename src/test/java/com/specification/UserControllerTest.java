package com.specification;


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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void addUser() {

    }


    @Test
    public void test_findAllUsers() throws Exception {
        Role mockRole = new Role(1, "admin");

        User mockUser = new User(1, "suraj@gmail.com", "Suraj Verma", 28, mockRole, null);

        when(userService.create(mockUser)).thenReturn(mockUser);
        mockMvc.perform(
                        post("/user/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(mockUser)))
                .andExpect(status().isOk());

    }

}
