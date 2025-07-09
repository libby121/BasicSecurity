package com.example.basicSecurity.service;

import com.example.basicSecurity.model.UserDTO;

import java.util.List;

/*
buisness logic
 */
public interface UserService {
    void addUsers(List<UserDTO> users);
    UserDTO getUser(int userId) throws Exception;
    UserDTO register(UserDTO u);
    String verify(UserDTO us);


}
