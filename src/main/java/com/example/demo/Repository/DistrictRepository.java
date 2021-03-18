package com.example.demo.Repository;

import com.example.demo.Entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DistrictRepository extends JpaRepository<District,Integer> {

List<District> findAllByRegion_Id(Integer region_id);

@Query(value = "select * from district where region_id=:districtId ",nativeQuery = true)
    List<District> districts(Integer districtId);


}
