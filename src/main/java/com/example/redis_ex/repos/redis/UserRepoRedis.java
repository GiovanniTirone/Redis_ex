package com.example.redis_ex.repos.redis;

import com.example.redis_ex.entities.redis.UserRedis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoRedis extends JpaRepository<UserRedis,Long> {



}
