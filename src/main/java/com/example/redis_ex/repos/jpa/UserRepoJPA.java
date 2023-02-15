package com.example.redis_ex.repos.jpa;

import com.example.redis_ex.entities.jpa.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepoJPA extends JpaRepository<UserJPA,Long> {
}
