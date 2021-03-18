package com.example.demo.Controller;

import com.example.demo.Entity.Region;
import com.example.demo.Payload.Regiondto;
import com.example.demo.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public List<Region> getRegions() {

        List<Region> regionList = regionRepository.findAll();

        return regionList;

    }

    @RequestMapping(value = "/region/{id}",method = RequestMethod.GET)
    public Region getRegion(@PathVariable Integer id){

        Optional<Region> optionalRegion = regionRepository.findById(id);

        if (optionalRegion.isPresent()){
            Region region = optionalRegion.get();
            return  region;

        }else {
            return new Region();
        }

    }

     @RequestMapping(value = "/region", method = RequestMethod.POST)
     public String addRegion(@RequestBody Regiondto regiondto) {

           Region region = new Region();

           region.setName(regiondto.getName());
           region.setArea(regiondto.getArea());
           region.setPopulation(regiondto.getPopulation());


          regionRepository.save(region);

          return "Region added";
     }





    @RequestMapping(value = "/region/{id}", method = RequestMethod.PUT)
    public String editRegion(@PathVariable Integer id, @RequestBody Regiondto regiondto) {

        Optional<Region> optionalRegion = regionRepository.findById(id);

        if (optionalRegion.isPresent()){

            Region region = optionalRegion.get();

            region.setName(regiondto.getName());
            region.setPopulation(regiondto.getPopulation());
            region.setArea(regiondto.getArea());

            regionRepository.save(region);

        }else {

            return "Region not found";

        }


        return "Region added";
    }



    @RequestMapping(value = "/region/{id}", method = RequestMethod.DELETE)
    public String deleteRegion(@PathVariable Integer id) {

         regionRepository.deleteById(id);

        return "Region deleted";

    }




}
