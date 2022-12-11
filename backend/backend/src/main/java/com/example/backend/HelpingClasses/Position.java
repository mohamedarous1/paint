package HelpingClasses;

import java.awt.*;

public class Position
{
    private double Posx;
    private double Posy;

    public MyPoint GetPosition()
    {
        return new MyPoint(this.Posx, this.Posy);
    }
    public Position(double x, double y)
    {
        this.Posx = x;
        this.Posy = y;
    }
}
