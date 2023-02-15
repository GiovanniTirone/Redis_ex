package com.example.redis_ex.services;

import com.example.redis_ex.entities.jpa.UserJPA;
import com.example.redis_ex.entities.redis.UserRedis;
import com.example.redis_ex.repos.jpa.UserRepoJPA;
import com.example.redis_ex.repos.redis.UserRepoRedis;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepoJPA userRepoJPA;

    @Autowired
    private UserRepoRedis userRepoRedis;

    @Autowired
    private UserFactory userFactory;

    public UserJPA create(UserJPA user) {
        if(user == null) return null;
        user.setId(null);
        return userRepoJPA.save(user);
    }

    public List<UserJPA> readAll() {
        return userRepoJPA.findAll();
    }

    public UserJPA readById(Long id) {
        Optional<UserRedis> userRedisOpt = userRepoRedis.findById(id);
        if(userRedisOpt.isPresent())
        {
            return userFactory.convertRedisToJPA(userRedisOpt.get());
        }
        else
        {
            Optional<UserJPA> userJPAOpt = userRepoJPA.findById(id);
            if(userJPAOpt.isPresent())
            {
                userRepoRedis.save(userFactory.convertJPAToRedis(userJPAOpt.get()));
                return userJPAOpt.get();
            }
            else
            {
                return null;
            }

        }
    }

    public void update(Long id, UserJPA userJPA) {
        userJPA.setId(id);
        UserRedis userRedis = userFactory.convertJPAToRedis(userJPA);
        userRepoJPA.save(userJPA);
        userRepoRedis.save(userRedis);
    }

    public void deleteById(Long id) {
        userRepoJPA.deleteById(id);
        userRepoRedis.deleteById(id);
    }

    public void readByIdFast(Long id) {
    }



}
