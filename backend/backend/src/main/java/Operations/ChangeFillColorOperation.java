package Operations;

import Shapes.ClosedShape;
import Shapes.Shape;
import org.json.simple.JSONObject;

public class ChangeFillColorOperation extends Operation
{
    private String OldColor;
    private String NewColor;

    public ChangeFillColorOperation(int id, String prevcolor, String newcolor)
    {
        super(id);
        this.OldColor = prevcolor;
        this.NewColor = newcolor;
    }

    @Override
    public void Execute(Shape shape)
    {
        ((ClosedShape)shape).SetFillColor(this.NewColor);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangeFillColorOperation ReversedOperation
                = new ChangeFillColorOperation(this.GetShapeID(), this.NewColor, this.OldColor);
        return ReversedOperation;
    }

    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "ChangeFillColorOperation");
        jsonObject.put("fill", this.NewColor);
        return jsonObject;
    }
}
