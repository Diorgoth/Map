package com.example.demo.Controller;



import com.example.demo.Entity.Address;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.Userss;
import com.example.demo.Payload.Regiondto;
import com.example.demo.Payload.Userdto;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CarRepository carRepository;



    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Userss> getUser() {

        List<Userss> userssList = userRepository.findAll();

        return userssList;

    }


    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Userss getUsers(@PathVariable Integer id) {

        Optional<Userss> optionalUserss = userRepository.findById(id);

        if (optionalUserss.isPresent()){

            return optionalUserss.get();

        }else {

            return new Userss();

        }

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(@RequestBody Userdto userdto) {

        Userss userss = new Userss();

        userss.setName(userdto.getName());

        Optional<Address> optionalAddress = addressRepository.findById(userdto.getAddress_id());

        if (!optionalAddress.isPresent()){

            return "Address not found";

        }

        userss.setAddress(optionalAddress.get());

        List<Integer> carList = userdto.getCarList();


        Set<Car> carSet = new HashSet<>();

        for (Integer carId: carList ) {

            Optional<Car> optionalCar =  carRepository.findById(carId);

            if(optionalCar.isPresent()){
                carSet.add(optionalCar.get());
            }
        }

        userss.setCarList(carSet);

        userRepository.save(userss);
        return "User added";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String editUser(@PathVariable Integer id, @RequestBody Userdto userdto) {

        Optional<Userss> optionalUserss = userRepository.findById(id);

        if (optionalUserss.isPresent()){

            Userss userss = optionalUserss.get();

            userss.setName(userdto.getName());

            Optional<Address> optionalAddress = addressRepository.findById(userdto.getAddress_id());

            if (!optionalAddress.isPresent()){

                return "Address not found";

            }

            userss.setAddress(optionalAddress.get());

            List<Integer> carList = userdto.getCarList();


            Set<Car> carSet = new HashSet<>();

            for (Integer carId: carList ) {

                Optional<Car> optionalCar =  carRepository.findById(carId);

                if(optionalCar.isPresent()){
                    carSet.add(optionalCar.get());
                }
            }

            userss.setCarList(carSet);

            userRepository.save(userss);
            return "User edited";

        }
          return "User not found";

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {

    userRepository.deleteById(id);

        return "User deleted";

    }



}
