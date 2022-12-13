package HelpingClasses;

import java.awt.*;

public class Scale
{
    private double Scalex;
    private double Scaley;

    public Scale(double x, double y)
    {
        this.Scalex = x;
        this.Scaley = y;
    }

    public void SetScale(double x, double y)
    {
        this.Scalex = x;
        this.Scaley = y;
    }

    public MyPoint GetScale()
    {
        return new MyPoint(this.Scalex, this.Scaley);
    }
}
