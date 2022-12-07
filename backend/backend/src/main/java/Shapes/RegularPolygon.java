package Shapes;

import HelpingClasses.CircleSize;
import HelpingClasses.LWSize;
import HelpingClasses.RegularPolygonSize;
import HelpingClasses.Size;

public abstract class RegularPolygon extends ClosedShape
{
    private RegularPolygonSize mySize;

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (RegularPolygonSize) size;
    }
}
