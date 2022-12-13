package Shapes;

import HelpingClasses.Position;
import HelpingClasses.RegularPolygonSize;
import HelpingClasses.Scale;
import System.MyCloneable;
import System.Convertable;
import org.json.simple.JSONObject;


public abstract class Shape implements MyCloneable
{
    private int ID;
    private boolean Enabled;
    private double StrokeWidth;
    private String StrokeColor;
    private Position ShapePosition;

    private Scale MyScale;

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

    @Override
    public void Clone(Shape shape)
    {
        shape.StrokeColor = this.StrokeColor;
        shape.StrokeWidth = this.StrokeWidth;
        shape.Enabled = this.Enabled;
    }
}
