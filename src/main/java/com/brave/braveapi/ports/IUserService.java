package com.brave.braveapi.ports;

import com.brave.braveapi.dto.UserDTO;

public interface IUserService {

    UserDTO find(String login, String password);

    UserDTO insert(UserDTO request);
}
