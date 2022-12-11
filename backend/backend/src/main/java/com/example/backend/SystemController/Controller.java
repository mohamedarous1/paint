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
    public int CreateNewObject
            (@PathVariable String shapetype, @PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);
        int id = MyService.Create(shapetype, NewJson);
        System.out.println("3roos");
        return id;
    }

    @GetMapping("/ChangePositionLine/{jsonObject}")
    public void ChangePositionLine(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangePositionLine(NewJson);
    }

    @GetMapping("/ResizeAndChangePosition/{jsonObject}")
    public void ResizeAndChangePosition(@PathVariable String jsonObject)
    {
        System.out.println(jsonObject);
        System.out.println(jsonObject);
        System.out.println(jsonObject);
        System.out.println(jsonObject);


        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);
        MyService.ResizeAndChangePosition(NewJson);
    }

    @GetMapping("/ChangeStrokeWidth/{jsonObject}")
    public void ChangeStrokeWidth(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangeStrokeWidth(NewJson);
    }

    @GetMapping("/ChangeStrokeColor/{jsonObject}")
    public void ChangeStrokeColor(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);

        MyService.ChangeStrokeColor(NewJson);
    }

    @GetMapping("/DisableShape/{jsonObject}")
    public void DisableShape(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJsoon(jsonObject);
        MyService.DisableShape(NewJson);
    }


    @GetMapping("/Undo")
    public JSONObject DisableShape()
    {
        System.out.println("hellloooooooo");
        JSONObject object = MyService.Undo();
        return object;
    }

}
