package Shapes;

import HelpingClasses.Position;
import HelpingClasses.Scale;
import System.MyCloneable;
import System.Convertable;
import org.json.simple.JSONObject;


public abstract class Shape implements MyCloneable, Convertable
{
    private int ID;
    private boolean Enabled;
    private double StrokeWidth;
    private String StrokeColor;
    private Position ShapePosition;
    private Scale MyScale;

    @Override
    public void Clone(Shape shape)
    {
        shape.SetStrokeColor(this.StrokeColor);
        shape.SetStrokeWidth(this.StrokeWidth);
        shape.Enabled = this.Enabled;
        shape.SetScale(this.MyScale);
        shape.SetPosition(this.ShapePosition);
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("stroke", this.StrokeColor);
        jsonObject.put("strokewidth", this.StrokeWidth);
        jsonObject.put("Enabled", this.Enabled);
        this.MyScale.PutObjectInJson(jsonObject);
        this.ShapePosition.PutObjectInJson(jsonObject);
    }

    public void SetScale(Scale scale)
    {
        this.MyScale = scale;
    }

    public Scale GetScale()
    {
        return this.MyScale;
    }

    public int GetId()
    {
        return this.ID;
    }
    public String GetStrokeColor()
    {
        return this.StrokeColor;
    }
    public void SetStrokeColor(String color)
    {
        this.StrokeColor = color;
    }
    public double GetStrokeWidth()
    {
        return this.StrokeWidth;
    }

    public void SetStrokeWidth(double width)
    {
        this.StrokeWidth = width;
    }
    public void SetEnabled()
    {
        this.Enabled = true;
    }
    public void SetDisabled()
    {
        this.Enabled = false;
    }
    public void SetPosition(Position position)
    {
        this.ShapePosition = position;
    }
    public Position GetPosition()
    {
        return this.ShapePosition;
    }
    public Shape(int id)
    {
        this.ID = id;
    }
}
