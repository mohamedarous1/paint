package Shapes;

import HelpingClasses.Sizes.CircleSize;
import HelpingClasses.Sizes.Size;

public class Circle extends ClosedShape
{
    public Circle(int id)
    {
        super(id);
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
    }

    @Override
    public void SetSize(Size size)
    {
        this.MySize = (CircleSize) size;
    }
    @Override
    public Size GetSize()
    {
        return this.MySize;
    }


}
