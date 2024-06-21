package com.mycompany.service.DI;

import com.mycompany.dto.UserDto;
import com.mycompany.model.Response;
import com.mycompany.model.Users;
import com.mycompany.repository.IUser;
import com.mycompany.service.itf.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUser iUser;

    @Override
    public List<UserDto> findAll() {
        List<UserDto> list = new ArrayList<>();
        List<Users> users = iUser.findAll();
        for (Users users1 : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(users1, userDto);
            list.add(userDto);
        }
        return list;
    }

    @Override
    public ResponseEntity findById(Long id) {
        UserDto userDto = new UserDto();
        Users users = iUser.findUsersById(id);
        if (users != null) {
            BeanUtils.copyProperties(users, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("ok", "ID can tim", userDto)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Flase", "ID can tim k co", userDto)
            );
        }
    }


    @Override
    public ResponseEntity save(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        if (iUser.checkName(userDto.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("flase", "Khong dc trung ten", null)
            );
        } else {
            Users users = modelMapper.map(userDto, Users.class);
            iUser.save(users);
            UserDto user = modelMapper.map(users, UserDto.class);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Them thanh cong", user)
            );
        }
    }

    @Override
    public ResponseEntity delete(Long id) {
        Users user = iUser.findUsersById(id);
        if (user != null) {
            user.setStatus(0);
            iUser.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Xoa thanh cong", user)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("flase", "Xoa khong thanh cong", user)
            );
        }
    }

    @Override
    public Users findUsersByUsername(String userName) {
        return iUser.checkName(userName);
    }
}
