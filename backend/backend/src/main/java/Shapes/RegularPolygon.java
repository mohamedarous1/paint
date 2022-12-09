package Shapes;

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

    @Override
    public Size GetSize()
    {
        return this.mySize;
    }

    public RegularPolygon(int id)
    {
        super(id);
    }


    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
    }
}
