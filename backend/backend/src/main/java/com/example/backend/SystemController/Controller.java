package com.example.backend.SystemController;
import ServicePackage.SystemService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import System.JsonConverter;
import System.MainSystem;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class Controller {

    public SystemService MyService  = new SystemService();

    @GetMapping("/create/{shapetype}/{jsonObject}")
    public int CreateNewObject
            (@PathVariable String shapetype, @PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
        int id = MyService.Create(shapetype, NewJson);
        return id;
    }

    @GetMapping("/ResizeAndChangePosition/{jsonObject}")
    public void ResizeAndChangePosition(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
        MyService.ResizeAndChangePosition(NewJson);
    }

    @GetMapping("/ChangeStrokeWidth/{jsonObject}")
    public void ChangeStrokeWidth(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);

        MyService.ChangeStrokeWidth(NewJson);
    }

    @GetMapping("/ChangeStrokeColor/{jsonObject}")
    public void ChangeStrokeColor(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);

        MyService.ChangeStrokeColor(NewJson);
    }

    @GetMapping("/ChangeFillColor/{jsonObject}")
    public void ChangeFillColor(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);

        MyService.ChangeFilColor(NewJson);
    }


    @GetMapping("/DisableShape/{jsonObject}")
    public void DisableShape(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
        MyService.DisableShape(NewJson);
    }

    @GetMapping("/Undo")
    public JSONObject Undo()
    {
        JSONObject object = MyService.Undo();
        return object;
    }

    @GetMapping("/Redo")
    public JSONObject Redo()
    {
        JSONObject object = MyService.Redo();
        return object;
    }

    @GetMapping("/save")
    public void save()
    {

//        ArrayList<JSONObject> Shapes =  MainSystem.SaveShapes();
//
//        System.out.println(Shapes.get(0).toString());

        //System.out.println(jsonObject);

//        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
//        MyService.CreateLine(NewJson);
    }

    //@GetMapping("/hesham/{temp}")
    @RequestMapping(value="/hesham", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public int Hesham(@RequestBody JSONObject temp)
    {

        //JSONObject NewJson = JsonConverter.GetJson(temp);

        //System.out.println(temp);

        ArrayList<Double> list = (ArrayList<Double>) temp.get("points");

        System.out.println(list.get(0));
        //System.out.println(NewJson);
//        MyService.ResizeAndChangePosition(NewJson);
        return 55555;
    }

}
