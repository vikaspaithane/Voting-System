package com.example.Crud.Operation.repository;

import com.example.Crud.Operation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select v from User v where v.email = :email")
    public User getUserByEmail(@Param("email") String email);


}
