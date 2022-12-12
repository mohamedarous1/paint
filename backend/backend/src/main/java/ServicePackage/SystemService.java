package ServicePackage;

import Operations.Operation;
import com.google.gson.Gson;
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

    public void ChangePositionLine(JSONObject obj)
    {
        MainSystem.ChangePositionLine(obj);
    }

    public void ResizeAndChangePosition(JSONObject obj)
    {
        MainSystem.ResizeAndChangePosition(obj);
    }
    public void ChangeStrokeWidth(JSONObject obj)
    {
        MainSystem.ChangeStrokeWidth(obj);
    }
    public void ChangeStrokeColor(JSONObject obj)
    {
        MainSystem.ChangeStrokeColor(obj);
    }

    public void ChangeFilColor(JSONObject obj)
    {
        MainSystem.ChangeFillColor(obj);
    }

    public void DisableShape(JSONObject obj)
    {
        MainSystem.DisableShape(obj);
    }

    public JSONObject Undo()
    {
        Operation operation = MainSystem.GetAndExecuteUndoOperation();
        JSONObject object = MainSystem.GenerateJsonForFrontEnd(operation);
        return object;
    }
}
