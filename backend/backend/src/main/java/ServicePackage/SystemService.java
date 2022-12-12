package ServicePackage;

import Operations.Operation;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import System.MainSystem;

@Service
public class SystemService
{
    public int Create(String ShapeType, JSONObject obj)
    {
        int ID = MainSystem.CreateNewObjectFrontAndEmptyUndo(ShapeType, obj);
        return ID;
    }

    public void ChangePositionLine(JSONObject obj)
    {
        MainSystem.ChangePositionLineAndEmtpryUndo(obj);
    }

    public void ResizeAndChangePosition(JSONObject obj)
    {
        MainSystem.ResizeAndChangePosition(obj);
    }
    public void ChangeStrokeWidth(JSONObject obj)
    {
        MainSystem.ChangeStrokeWidthAndEmtpryUndo(obj);
    }
    public void ChangeStrokeColor(JSONObject obj)
    {
        MainSystem.ChangeStrokeColorAndEmtpryUndo(obj);
    }

    public void ChangeFilColor(JSONObject obj)
    {
        MainSystem.ChangeFillColorAndEmtpryUndo(obj);
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

    public JSONObject Redo()
    {
        Operation operation = MainSystem.GetAndExecuteRedoOperation();
        JSONObject object = MainSystem.GenerateJsonForFrontEnd(operation);
        return object;
    }
}
