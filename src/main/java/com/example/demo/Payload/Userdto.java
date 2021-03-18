package com.example.demo.Payload;


import com.example.demo.Entity.Car;
import lombok.Data;


import java.util.List;

@Data
public class Userdto {





    private String name;


    private List<Integer> carList;

    private Integer address_id;

}
