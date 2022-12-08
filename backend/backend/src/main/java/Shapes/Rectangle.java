package Shapes;

import HelpingClasses.*;

public class Rectangle extends ClosedShape
{
    private LWSize mySize;

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (LWSize)size;
    }
}
