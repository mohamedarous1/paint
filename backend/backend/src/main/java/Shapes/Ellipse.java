package Shapes;

import HelpingClasses.Sizes.*;

public class Ellipse extends ClosedShape
{
    public Ellipse(int id)
    {
        super(id);
    }

    public void SetSize(Size size)
    {
        this.SetTriangleSize((EllipseSize) size);
    }

    private void SetTriangleSize(EllipseSize size)
    {
        this.MySize = size;
    }
}
