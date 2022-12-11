package com.example.backend.SystemController;
import ServicePackage.SystemService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Controller {

    public SystemService MyService  = new SystemService();

//    @Autowired
//    public Controller(SystemService service)
//    {
//        //this.MyService = service;
//    }

    @GetMapping("/create/{jsonObject}")
    public String CreateNewObject(@PathVariable String jsonObject)
    {
        System.out.println(jsonObject);
        JSONObject hh = new Gson().fromJson(jsonObject , JSONObject.class);
        int id = MyService.Create(hh);
        return Integer.toString(id);
    }
}
