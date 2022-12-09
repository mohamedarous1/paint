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

    @Override
    public Size GetSize()
    {
        return this.mySize;
    }

    public Ellipse(int id)
    {
        super(id);
    }
}
