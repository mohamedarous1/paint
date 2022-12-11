package com.example.backend.SystemController;
import ServicePackage.SystemService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Shapes.*;


import System.MainSystem;

@RestController
@CrossOrigin
public class Controller {

    public SystemService MyService  = new SystemService();

//    @Autowired
//    public Controller(SystemService service)
//    {
//        //this.MyService = service;
//    }

    @GetMapping("/create/{shapetype}/{jsonObject}")
    @CrossOrigin
    public int CreateNewObject(@PathVariable String shapetype, @PathVariable String jsonObject)
    {
        JSONObject NewJson = new Gson().fromJson(jsonObject , JSONObject.class);


        int id = MyService.Create(shapetype, NewJson);
        //System.out.println(id);

        return id;
    }
}
