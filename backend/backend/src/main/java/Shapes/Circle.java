package Shapes;

import HelpingClasses.*;

public class Circle extends ClosedShape
{
    private CircleSize mySize;

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (CircleSize) size;
    }
}
