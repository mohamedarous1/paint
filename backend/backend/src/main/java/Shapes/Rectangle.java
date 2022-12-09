package Shapes;

import HelpingClasses.*;
import org.w3c.dom.css.Rect;

public class Rectangle extends ClosedShape
{
    private LWSize mySize;

    @Override
    public void SetSize(Size size)
    {
        this.mySize = (LWSize)size;
    }

    public Rectangle(int id)
    {
        super(id);
    }
}
