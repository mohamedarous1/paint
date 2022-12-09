package Shapes;

import HelpingClasses.*;

public class Circle extends ClosedShape
{
    private CircleSize mySize;

    public Circle(int id)
    {
        super(id);
    }
    @Override
    public void SetSize(Size size)
    {
        this.mySize = (CircleSize) size;
    }
    @Override
    public Size GetSize()
    {
        return this.mySize;
    }


    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
    }

}
