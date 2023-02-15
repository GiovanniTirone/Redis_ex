package com.example.redis_ex.services;

import com.example.redis_ex.entities.jpa.UserJPA;
import com.example.redis_ex.entities.redis.UserRedis;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    public UserJPA convertRedisToJPA (UserRedis userRedis) {
        UserJPA userJPA = new UserJPA();
        userJPA.setId(userRedis.getId());
        userJPA.setFirstName(userRedis.getFirstName());
        userJPA.setLastName(userRedis.getLastName());
        userJPA.setProfilePicture(userRedis.getProfilePicture());
        return userJPA;
    }

    public UserRedis convertJPAToRedis (UserJPA userJPA) {
        UserRedis userRedis = new UserRedis();
        userRedis.setId(userJPA.getId());
        userRedis.setFirstName(userJPA.getFirstName());
        userRedis.setProfilePicture(userJPA.getProfilePicture());
        return userRedis;
    }


}
