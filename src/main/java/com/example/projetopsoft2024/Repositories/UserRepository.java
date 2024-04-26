package com.example.projetopsoft2024.Repositories;


import com.example.projetopsoft2024.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT f from User f where f.name LIKE :name")
    List<User> findByName(@Param("name") String name);

}


