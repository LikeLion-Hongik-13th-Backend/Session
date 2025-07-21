package com.example.shop.repository;


import com.example.shop.entity.SocialType;
import com.example.shop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);

    Optional<User> findBySocialId(String socialId);

}
