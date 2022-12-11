package Shapes;

import HelpingClasses.*;
import org.w3c.dom.css.Rect;

public class Rectangle extends ClosedShape
{
    private LWSize mySize;

    public Rectangle(int id)
    {
        super(id);
    }

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (LWSize)size;
    }

    @Override
    public Size GetSize()
    {
        return this.mySize;
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
    }
}
