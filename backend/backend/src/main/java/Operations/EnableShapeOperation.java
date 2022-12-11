package Operations;

import Shapes.Shape;
import org.json.simple.JSONObject;

public class EnableShapeOperation extends Operation
{
    public EnableShapeOperation(int id)
    {
        super(id);
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetEnabled();
    }
    @Override
    public Operation GetReversedOperation()
    {
        DisableShapeOperation ReversedOperation
                = new DisableShapeOperation(this.GetShapeID());
        return ReversedOperation;
    }

    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "EnableShapeOperation");
        return jsonObject;
    }
}
