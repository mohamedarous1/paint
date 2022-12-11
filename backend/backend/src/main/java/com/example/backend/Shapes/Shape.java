package Shapes;

import HelpingClasses.Position;
import System.MyCloneable;

public abstract class Shape implements MyCloneable
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
    public void SetPosition(Position position)
    {
        this.ShapePosition = position;
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