package com.example.demo.Controller;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.District;

import com.example.demo.Entity.Region;
import com.example.demo.Payload.Districtdto;
import com.example.demo.Payload.Regiondto;
import com.example.demo.Repository.DistrictRepository;

import com.example.demo.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController

public class DistrictController {

     @Autowired
     DistrictRepository districtRepository;
     @Autowired
    RegionRepository regionRepository;

    @RequestMapping(value = "/district", method = RequestMethod.GET)
    public List<District> getDistrict() {

        List<District> districtList = districtRepository.findAll();

        return districtList;

    }


    @RequestMapping(value = "/district/{id}",method = RequestMethod.GET)
    public District getDistrict(@PathVariable Integer id){

        Optional<District> optionalDistrict = districtRepository.findById(id);


        if (optionalDistrict.isPresent()){

            District district = optionalDistrict.get();

            return district;
        }else {

            return new District();

        }



    }

    @RequestMapping(value = "/districts/{id}",method = RequestMethod.GET)
    public List<District> getDistrictsByRegionId(@PathVariable Integer id){

        List<District> districtList = districtRepository.districts(id);

        return districtList;

    }



    @RequestMapping(value = "/district", method = RequestMethod.POST)
    public String addDistrict(@RequestBody Districtdto districtdto) {


        District district = new District();

        district.setName(districtdto.getName());

        district.setArea(districtdto.getArea());

        district.setPopulation(districtdto.getPopulation());

        Optional<Region> optionalRegion = regionRepository.findById(districtdto.getRegion_id());

        if (!optionalRegion.isPresent()) {
            return "Region topilmadi";
        }


        district.setRegion(optionalRegion.get());

        districtRepository.save(district);

        return "District added";


    }


    @RequestMapping(value = "/district/{id}", method = RequestMethod.PUT)
    public String editDistrict(@PathVariable Integer id, @RequestBody Districtdto districtdto) {


        Optional<District> optionalDistrict = districtRepository.findById(id);

        if (optionalDistrict.isPresent()){

            District district = optionalDistrict.get();

            district.setName(districtdto.getName());

            district.setArea(districtdto.getArea());

            district.setPopulation(districtdto.getPopulation());

            Optional<Region> optionalRegion = regionRepository.findById(districtdto.getRegion_id());

            if (optionalDistrict.isPresent()){

                district.setRegion(optionalRegion.get());

            }else {
                return "Region not found";
            }

            districtRepository.save(district);

                return "District edited";
        }

        return "District not found";

    }

    @RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
    public String deleteDistrict(@PathVariable Integer id) {

        districtRepository.deleteById(id);

        return "District deleted";

    }




}
