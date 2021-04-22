package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
        UserEntity findByUserName(String userName);
        UserEntity findByEmailAndIDNot(String email,Integer id);

}
