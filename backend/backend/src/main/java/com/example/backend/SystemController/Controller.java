package com.example.backend.SystemController;
import ServicePackage.SystemService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import System.JsonConverter;

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

    @GetMapping("/CreateLine/{jsonObject}")
    public void CreateLine(@PathVariable String jsonObject)
    {
        JSONObject NewJson = JsonConverter.GetJson(jsonObject);
        MyService.CreateLine(NewJson);
    }


}
