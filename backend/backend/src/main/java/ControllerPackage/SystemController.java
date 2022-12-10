package ControllerPackage;

import ServicePackage.SystemService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Paint/")
public class SystemController
{
    public SystemService MyService;

    @Autowired
    public SystemController(SystemService service)
    {
        this.MyService = service;
    }

    @GetMapping("/create/jsonObject")
    public String CreateNewObject(@RequestParam JSONObject jsonObject)
    {
        int id = MyService.Create(jsonObject);
        return Integer.toString(id);
    }
}
