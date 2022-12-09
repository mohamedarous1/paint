package Shapes;

import HelpingClasses.*;

public abstract class ClosedShape extends Shape
{
    protected String FilColor;

    public ClosedShape(int id)
    {
        super(id);
    }

    public void SetFillColor(String color)
    {
        this.FilColor = color;
    }
    public String GetFillColor()
    {
        return this.FilColor;
    }

    public abstract void SetSize(Size s);

    public abstract Size GetSize();

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
        ClosedShape ToBeCloned = (ClosedShape)shape;

        ToBeCloned.SetFillColor( this.GetFillColor());
        ToBeCloned.SetSize(this.GetSize());
    }
}
