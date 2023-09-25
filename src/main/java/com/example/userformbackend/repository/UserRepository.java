package com.example.userformbackend.repository;

import com.example.userformbackend.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserData,Integer> {

    @Query(value = "SELECT * FROM USER_DATA WHERE FIRSTNAME = :firstName AND LASTNAME =:lastName" , nativeQuery = true)
    public List<UserData> checkExistingUser(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
