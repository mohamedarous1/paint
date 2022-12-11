package ServicePackage;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import System.MainSystem;

@Service
public class SystemService
{
    public int Create(String ShapeType, JSONObject obj)
    {
        int ID = MainSystem.CreateNewObjectFront(ShapeType, obj);
        return ID;
    }
}
