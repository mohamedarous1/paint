package Shapes;

import HelpingClasses.Sizes.*;

public class Square extends ClosedShape
{
    public Square(int id)
    {
        super(id);
    }

    public void SetSize(Size size)
    {
        this.SetTriangleSize((SquareSize) size);
    }

    private void SetTriangleSize(SquareSize size)
    {
        this.MySize = size;
    }

}
