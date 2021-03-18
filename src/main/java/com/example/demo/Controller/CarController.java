package com.example.demo.Controller;

import com.example.demo.Entity.Car;
import com.example.demo.Entity.Region;
import com.example.demo.Entity.Userss;
import com.example.demo.Payload.Cardto;


import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.RegionRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public List<Car> getCar() {

        List<Car> carList = carRepository.findAll();

        return carList;

    }


    @RequestMapping(value = "/car/{id}",method = RequestMethod.GET)
    public Car getCarss(@PathVariable Integer id) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()){

            return optionalCar.get();

        }else {

            return new Car();

        }

    }

    @RequestMapping(value = "/car/{region_id}",method = RequestMethod.GET)
    public List<Car> getCarsss(@PathVariable Integer region_id) {

        List<Car> carList = carRepository.findAllByRegion_Id(region_id);

        return carList;

    }

    @RequestMapping(value = "/car/{user_id}",method = RequestMethod.GET)
    public List<Car> getCarssss(@PathVariable Integer user_id) {

        List<Car> byUserId = carRepository.carsByUserId(user_id);

        return byUserId;

    }



    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public String addCar(@RequestBody Cardto cardto) {

        Car car = new Car();

        car.setMadeYear(cardto.getMadeYear());
        car.setModel(cardto.getModel());
        car.setType(cardto.getType());
        car.setStateNumber(cardto.getStateNumber());

        Optional<Region> optionalRegion = regionRepository.findById(cardto.getRegion_id());

        if (!optionalRegion.isPresent()){

         return "Region not found";

        }
        car.setRegion(optionalRegion.get());

        Optional<Userss> optionalUserss = userRepository.findById(cardto.getUser_id());

        if (!optionalUserss.isPresent()){

            return "User not found";

        }

        car.setUser(optionalUserss.get());

        carRepository.save(car);


        return "Car added";
    }



    @RequestMapping(value = "/car/{id}", method = RequestMethod.PUT)
    public String editCar(@PathVariable Integer id, @RequestBody Cardto cardto) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()){
            Car car = optionalCar.get();

            car.setMadeYear(cardto.getMadeYear());
            car.setModel(cardto.getModel());
            car.setType(cardto.getType());
            car.setStateNumber(cardto.getStateNumber());

            Optional<Region> optionalRegion = regionRepository.findById(cardto.getRegion_id());

            if (!optionalRegion.isPresent()){

                return "Region not found";

            }
            car.setRegion(optionalRegion.get());

            Optional<Userss> optionalUserss = userRepository.findById(cardto.getUser_id());

            if (!optionalUserss.isPresent()){

                return "User not found";

            }

            car.setUser(optionalUserss.get());

            carRepository.save(car);

             return "Car edited";


        }

    return "Car not found";

    }


    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public String deleteCar(@PathVariable Integer id) {

        carRepository.deleteById(id);

        return "Car deleted";

    }


}
