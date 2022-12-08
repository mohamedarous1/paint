package Shapes;

import HelpingClasses.LWSize;
import HelpingClasses.Size;

public class Ellipse extends ClosedShape
{
    private LWSize mySize;

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (LWSize) size;
    }
}
