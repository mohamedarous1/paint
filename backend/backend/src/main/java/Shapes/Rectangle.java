package Shapes;

import HelpingClasses.Sizes.RectangleSize;
import HelpingClasses.Sizes.Size;
import HelpingClasses.Sizes.SquareSize;

public class Rectangle extends ClosedShape
{
    public Rectangle(int id)
    {
        super(id);
    }

    public void SetSize(Size size)
    {
        this.SetTriangleSize((RectangleSize) size);
    }

    private void SetTriangleSize(RectangleSize size)
    {
        this.MySize = size;
    }
}
