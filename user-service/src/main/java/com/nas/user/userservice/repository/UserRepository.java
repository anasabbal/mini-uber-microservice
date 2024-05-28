package com.nas.user.userservice.repository;


import com.nas.user.userservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
