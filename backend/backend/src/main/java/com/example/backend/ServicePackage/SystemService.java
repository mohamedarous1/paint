package ServicePackage;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import System.MainSystem;

@Service
public class SystemService
{
    public int Create(JSONObject obj)
    {
        int ID = MainSystem.CreateNewObjectFront(obj);
        return ID;
    }
}
