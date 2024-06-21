package com.mycompany.dto;

import com.mycompany.model.Users;
import lombok.Data;

@Data
public class UserDto {
    private String username ;
    private String password ;
    private int status ;
}
