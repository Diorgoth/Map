package com.example.demo.Repository;

import com.example.demo.Entity.Userss;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<Userss, Integer> {



}
