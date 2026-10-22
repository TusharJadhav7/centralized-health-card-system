package com.avirat.chc.repo;

import com.avirat.chc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    public Optional<UserEntity>  findByUserName(String userName);

    @Query(value = "select*from chc.user_info where user_name=? and user_health_card_no=?;",nativeQuery = true)
    public Optional<UserEntity> getUserNameDataCheck(String userName, Integer healthCardNo);

    public Optional<UserEntity> findBydoctorRegNo (Long doctorRegNo);

    public   Optional<UserEntity>  findByHealthCardNo(Integer healthCardNo);



}

