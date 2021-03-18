package com.example.demo.Repository;

import com.example.demo.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findAllByRegion_Id(Integer region_id);

    @Query(value = "select * from car where user_id:user_id",nativeQuery = true)
    List<Car>  carsByUserId(Integer user_id);

}
