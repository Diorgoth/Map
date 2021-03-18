package com.example.demo.Controller;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.District;
import com.example.demo.Entity.Region;
import com.example.demo.Payload.Addressdto;
import com.example.demo.Payload.Districtdto;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistrictRepository districtRepository;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List<Address> getAddress() {

        List<Address> addressList = addressRepository.findAll();

        return addressList;

    }


    @RequestMapping(value = "/address/{id}",method = RequestMethod.GET)
    public Address getAddress(@PathVariable Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);


        if (optionalAddress.isPresent()) {

            Address address = optionalAddress.get();

            return address;
        } else {

            return new Address();

        }
    }

    @RequestMapping(value = "/addresses/{id}",method = RequestMethod.GET)
    public List<Address> getAddressesByDistrictId(@PathVariable Integer id){

        List<Address> addressList = addressRepository.adress(id);

        return addressList;

    }


    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String addAddress(@RequestBody Addressdto addressdto) {


        Address address = new Address();

        address.setStreet_name(addressdto.getStreet_name());
        address.setHome_number(addressdto.getHome_number());

        Optional<District> optionalDistrict = districtRepository.findById(addressdto.getDistrict_id());

        if (optionalDistrict.isPresent()){


            address.setDistrict(optionalDistrict.get());


        }else {

            return "District not found";

        }

        addressRepository.save(address);

        return "District added";


    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public String editAddress(@PathVariable Integer id, @RequestBody Addressdto addressdto) {


        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()){

            Address address = optionalAddress.get();

            address.setStreet_name(addressdto.getStreet_name());

            address.setHome_number(addressdto.getHome_number());

            Optional<District> optionalDistrict = districtRepository.findById(addressdto.getDistrict_id());

            if (optionalDistrict.isPresent()){

                address.setDistrict(optionalDistrict.get());

            }else {

                return "District not found";

            }

            addressRepository.save(address);

            return "Address edited";

        }

        return "Address not found";


    }




    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Integer id) {

       addressRepository.deleteById(id);

        return "Address deleted";

    }





    }
