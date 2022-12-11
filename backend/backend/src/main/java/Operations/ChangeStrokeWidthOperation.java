package Operations;


import Shapes.Shape;
import org.json.simple.JSONObject;

public class ChangeStrokeWidthOperation extends Operation
{
    private double OldStrokeWidth;
    private double NewStrokeWidth;

    public ChangeStrokeWidthOperation(int id, double prevwidth, double newwidth)
    {
        super(id);
        this.OldStrokeWidth = prevwidth;
        this.NewStrokeWidth = newwidth;
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetStrokeWidth(this.NewStrokeWidth);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangeStrokeWidthOperation ReversedOperation
                = new ChangeStrokeWidthOperation(this.GetShapeID(), this.NewStrokeWidth, this.OldStrokeWidth);
        return ReversedOperation;
    }



    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "ChangeStrokeWidthOperation");
        jsonObject.put("strokeWidth", this.NewStrokeWidth);
        return jsonObject;
    }
}
