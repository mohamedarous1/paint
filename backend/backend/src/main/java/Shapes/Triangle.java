package Shapes;

import HelpingClasses.Sizes.*;

public class Triangle extends ClosedShape
{
    public Triangle(int id)
    {
        super(id);
    }

    public void SetSize(Size size)
    {
        this.SetTriangleSize((TriangleSize) size);
    }

    private void SetTriangleSize(TriangleSize size)
    {
        this.MySize = size;
    }

}

