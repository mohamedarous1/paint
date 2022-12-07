package Shapes;

import HelpingClasses.*;

public abstract class ClosedShape extends Shape
{
    protected String FilColor;
    protected Position ShapePosition;

    public void SetFillColor(String color)
    {
        this.FilColor = color;
    }
    public void SetPos(int x, int y)
    {
        this.ShapePosition = new Position(x, y);
    }
    public void SetSize(Size s)
    {
    }
}
