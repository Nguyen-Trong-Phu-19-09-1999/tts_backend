package com.mycompany.service.itf;


import com.mycompany.dto.UserDto;
import com.mycompany.model.Users;

public interface IUserService extends IGenerateService<UserDto> {
    Users findUsersByUsername(String userName) ;
}
