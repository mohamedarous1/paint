package Shapes;

import HelpingClasses.*;

public abstract class ClosedShape extends Shape
{
    protected String FilColor;

    public void SetFillColor(String color)
    {
        this.FilColor = color;
    }
    public String GetFillColor()
    {
        return this.FilColor;
    }

    public void SetSize(Size s)
    {
    }

    public Size GetSize()
    {
        return null; // Overrided Function
    }

    public ClosedShape(int id)
    {
        super(id);
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
        ClosedShape ToBeCloned = (ClosedShape)shape;

        ToBeCloned.SetFillColor( this.GetFillColor());
        ToBeCloned.SetSize(this.GetSize());
    }
}
