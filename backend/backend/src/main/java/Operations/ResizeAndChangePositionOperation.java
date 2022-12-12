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
        String ShapeType = MainSystem.GetShapeType(this.GetShapeID());

        if (ShapeType == "Square")
        {
            RegularPolygonSize size = (RegularPolygonSize) this.NewSize;
            double x = size.GetSideLength();
            jsonObject.put("height", x);
            jsonObject.put("width", x);
        }
        else if (ShapeType == "Rectangle")
        {
            LWSize size = (LWSize) this.NewSize;
            double x = size.GetLWSize().Getx();
            double y = size.GetLWSize().Gety();

            jsonObject.put("height", x);
            jsonObject.put("width", y);
        }
        else if (ShapeType == "Circle")
        {
            CircleSize size = (CircleSize) this.NewSize;
            double x = size.GetRadius();
            jsonObject.put("radius", x);
        }
        else if (ShapeType == "Ellipse")
        {
            LWSize size = (LWSize) this.NewSize;
            double x = size.GetLWSize().Getx();
            double y = size.GetLWSize().Gety();
            jsonObject.put("radiusX", x);
            jsonObject.put("radiusY", y);
        }
        else if (ShapeType == "Triangle" || ShapeType == "Pentagon")
        {
            RegularPolygonSize size = (RegularPolygonSize) this.NewSize;
            double x = size.GetSideLength();
            jsonObject.put("radius", x);
        }

    }
}
