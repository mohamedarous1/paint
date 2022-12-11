package Operations;

import Shapes.Shape;
import org.json.simple.JSONObject;

public class DisableShapeOperation extends Operation
{
    public DisableShapeOperation(int id)
    {
        super(id);
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetDisabled();
    }
    @Override
    public Operation GetReversedOperation()
    {
        EnableShapeOperation ReversedOperation
                = new EnableShapeOperation(this.GetShapeID());
        return ReversedOperation;
    }


    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "DisableShapeOperation");
        return jsonObject;
    }

}
