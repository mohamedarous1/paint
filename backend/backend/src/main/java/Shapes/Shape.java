package Shapes;

import HelpingClasses.Position;
import System.MyCloneable;

public abstract class Shape
{
    private int ID;
    private boolean Enabled;
    protected int StrokeWidth;
    protected String StrokeColor;
    protected Position ShapePosition;

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
    public int GetStrokeWidth()
    {
        return this.StrokeWidth;
    }
    public void SetStrokeWidth(int width)
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
    public void SetPosition(int x, int y)
    {
        this.ShapePosition = new Position(x, y);
    }

    public Shape(int id)
    {
        this.ID = id;
    }

//
    protected Object clone(Shape shape)
    {
        shape.StrokeColor = this.StrokeColor;
        shape.StrokeWidth = this.StrokeWidth;
        shape.Enabled = this.Enabled;
        return shape;
    }

}
