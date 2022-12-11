package com.example.backend.SystemController;
import ServicePackage.SystemService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Shapes.*;
import System.JsonConverter;

import System.MainSystem;

@RestController
@CrossOrigin
public class Controller {

    public SystemService MyService  = new SystemService();

    @GetMapping("/create/{shapetype}/{jsonObject}")
    @CrossOrigin
    public int CreateNewObject
            (@PathVariable String shapetype, @PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);
        int id = MyService.Create(shapetype, NewJson);

        return id;
    }

    @GetMapping("/ChangePositionLine/{jsonObject}")
    @CrossOrigin
    public void ChangePositionLine(String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangePositionLine(NewJson);
    }

    @GetMapping("/ResizeAndChangePosition/{jsonObject}")
    @CrossOrigin
    public void ResizeAndChangePosition(String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);
        System.out.println("hello");
        MyService.ResizeAndChangePosition(NewJson);
    }

    @GetMapping("/ChangeStrokeWidth/{jsonObject}")
    @CrossOrigin
    public void ChangeStrokeWidth(String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangeStrokeWidth(NewJson);
    }

    @GetMapping("/ChangeStrokeColor/{jsonObject}")
    @CrossOrigin
    public void ChangeStrokeColor(String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangeStrokeColor(NewJson);
    }

    @GetMapping("/DisableShape/{jsonObject}")
    @CrossOrigin
    public void DisableShape(String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.DisableShape(NewJson);
    }

}
