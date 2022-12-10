package HelpingClasses;

import java.awt.*;

public class LWSize extends Size
{
    private double Length;
    private double Width;
    public MyPoint GetLWSize()
    {
        return new MyPoint(this.Length, this.Width);
    }
    public LWSize(double lenth, double width)
    {
        this.Length = lenth;
        this.Width = width;
    }
}
