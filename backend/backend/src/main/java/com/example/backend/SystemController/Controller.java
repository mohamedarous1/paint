package com.example.backend.SystemController;
import ServicePackage.SystemService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import System.JsonConverter;
import System.*;

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
        System.out.println(id);
        System.out.println(id);
        System.out.println(id);

        return id;
    }

//    @RequestMapping(value="/create"
//            , method=RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public int CreateNewObject
//            (@RequestBody String shapetype, @RequestBody String jsonObject)
//    {
//        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
//        int id = MyService.Create(shapetype, NewJson);
//        return id;
//    }

    //@GetMapping("/ResizeAndChangePosition/{jsonObject}")
    @RequestMapping(value="/ResizeAndChangePosition"
            , method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void ResizeAndChangePosition(@RequestBody String jsonObject)
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

    @RequestMapping(value="/CreateLine", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public int CreateLine(@RequestBody JSONObject temp)
    {
        int id = MyService.CreateLine(temp);
        return id;
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


    @RequestMapping(value = "/save/{fileName}")
    public void saveXml(@PathVariable String fileName)
    {
        if (fileName.contains(".xml"))
            FileHandler.writeXml(fileName, MainSystem.SaveShapes());
        else
            FileHandler.writeJson(fileName, MainSystem.SaveShapes());
    }

    @RequestMapping(value = "/load/{fileName}")
    public String loadXml(@PathVariable String fileName)
    {
        String s = "";
        if (fileName.contains(".xml"))
            s = FileHandler.readXml("./"+fileName);
        else
            s = FileHandler.readJson("./" + fileName);
        MyService.ClearService();
        return s;
    }

    @GetMapping("/clear")
    public void ClearRequest()
    {
        MyService.ClearService();
    }

}
