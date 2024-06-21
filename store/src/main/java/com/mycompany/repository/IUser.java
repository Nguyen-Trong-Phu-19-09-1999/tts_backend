package com.mycompany.repository;

import com.mycompany.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUser extends JpaRepository<Users, Long> {
    Users findUsersById(Long id);

    @Query(value = "SELECT u FROM Users u WHERE u.username = :userName")
    Users checkName(String userName) ;
}

