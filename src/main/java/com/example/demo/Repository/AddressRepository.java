package com.example.demo.Repository;

import com.example.demo.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    List<Address> findAllByDistrict_Id(Integer district_id);

    @Query(value = "select * from address where district_id=:addressId",nativeQuery = true)
    List<Address> adress(Integer addressId);
}
