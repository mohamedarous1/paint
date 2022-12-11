package ControllerPackage;

import ServicePackage.SystemService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;


@RestController
@CrossOrigin
public class SystemController
{
    public SystemService MyService;

    //@Autowired
    //public SystemController(SystemService service)
    //{
    //    this.MyService = service;
    //}

    @GetMapping("/create")
    public String CreateNewObject()//@PathVariable String jsonObject)
    {
        //System.out.println(jsonObject);
        //JSONObject hh = new Gson().fromJson(jsonObject , JSONObject.class);
         //int id = 5;
        //int id = MyService.Create(jsonObject);
        //return Integer.toString(id);
        return "hello";
    }
}
