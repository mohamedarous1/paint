package Operations;

import HelpingClasses.*;
import Shapes.ClosedShape;
import Shapes.RegularPolygon;
import Shapes.Shape;
import org.json.simple.JSONObject;
import System.MainSystem;

import java.awt.*;

public class ResizeAndChangePositionOperation extends Operation
{
    private Size OldSize;
    private Size NewSize;
    private Position OldPosition;
    private Position NewPosition;

    public ResizeAndChangePositionOperation
            (int id, Position prevposition, Position newposition, Size oldsize, Size newsize)
    {
        super(id);
        this.OldPosition = prevposition;
        this.NewPosition = newposition;
        this.OldSize = oldsize;
        this.NewSize = newsize;
    }

    @Override
    public void Execute(Shape shape)
    {
        ClosedShape closedShape = (ClosedShape) shape;

        closedShape.SetPosition(NewPosition);
        closedShape.SetSize(NewSize);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ResizeAndChangePositionOperation ReversedOperation
                = new ResizeAndChangePositionOperation
                (this.GetShapeID(), this.NewPosition, this.OldPosition, this.NewSize, this.OldSize);
        return ReversedOperation;
    }


    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "ResizeAndChangePositionOperation");
        MyPoint point = NewPosition.GetPosition();
        jsonObject.put("x", point.Getx());
        jsonObject.put("y", point.Gety());
        this.PutSizeInJson(jsonObject);
        return jsonObject;
    }

    private void PutSizeInJson(JSONObject jsonObject)
    {
        Size size = MainSystem.GetShapeSizeById(this.GetShapeID());

        size.ConvertObjectToJson(jsonObject);
    }
}
