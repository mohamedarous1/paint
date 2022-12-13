package Operations;

import HelpingClasses.*;
import HelpingClasses.Sizes.Size;
import Shapes.ClosedShape;
import Shapes.Shape;
import org.json.simple.JSONObject;
import System.MainSystem;

public class RescaleAndChangePositionOperation extends Operation
{
    private Scale OldScale;
    private Scale NewScale;

    private Position OldPosition;
    private Position NewPosition;

    public RescaleAndChangePositionOperation
            (int id, Position prevposition, Position newposition, Scale oldscale, Scale newscale)
    {
        super(id);
        this.OldPosition = prevposition;
        this.NewPosition = newposition;
        this.OldScale = oldscale;
        this.NewScale = newscale;
    }

    @Override
    public void Execute(Shape shape)
    {
        ClosedShape closedShape = (ClosedShape) shape;

        closedShape.SetPosition(NewPosition);
        closedShape.SetScale(NewScale);
    }
    @Override
    public Operation GetReversedOperation()
    {
        RescaleAndChangePositionOperation ReversedOperation
                = new RescaleAndChangePositionOperation
                (this.GetShapeID(), this.NewPosition, this.OldPosition, this.NewScale, this.OldScale);
        return ReversedOperation;
    }

    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "RescaleAndChangePositionOperation");
        this.NewPosition.PutObjectInJson(jsonObject);
        this.NewScale.PutObjectInJson(jsonObject);
        return jsonObject;
    }

    private void PutSizeInJson(JSONObject jsonObject)
    {
        Size size = MainSystem.GetShapeSizeById(this.GetShapeID());

        size.PutObjectInJson(jsonObject);
    }
}
