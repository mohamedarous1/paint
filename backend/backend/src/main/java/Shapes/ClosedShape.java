package Shapes;

import HelpingClasses.*;

public abstract class ClosedShape extends Shape
{
    protected String FilColor;

    public void SetFillColor(String color)
    {
        this.FilColor = color;
    }

    public void SetSize(Size s)
    {
    }

    public ClosedShape(int id)
    {
        super(id);
    }
}
