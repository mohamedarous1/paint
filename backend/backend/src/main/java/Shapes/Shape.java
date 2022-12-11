package Shapes;

import HelpingClasses.Position;
import System.MyCloneable;

public abstract class Shape implements MyCloneable
{
    private int ID;
    private boolean Enabled;
    protected double StrokeWidth;
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
