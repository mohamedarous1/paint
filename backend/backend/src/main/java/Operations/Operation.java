package Operations;

import Shapes.Shape;
import org.json.simple.JSONObject;

public abstract class Operation
{
    private int ShapeID;

    public int GetShapeID()
    {
        return this.ShapeID;
    }
    public Operation(int id)
    {
        this.ShapeID = id;
    }
    public abstract void Execute(Shape shape);

    public abstract Operation GetReversedOperation();

    public JSONObject GetJsonForFrontend()
    {
        JSONObject obj = new JSONObject();
        obj.put("id", this.ShapeID);
        return obj;
    }
}
