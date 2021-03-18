package com.example.demo.Repository;

import com.example.demo.Entity.District;
import com.example.demo.Entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Integer> {




}
