package HelpingClasses;

import java.awt.*;

public class Position
{
    private int Posx;
    private int Posy;

    public Point GetPosition()
    {
        return new Point(this.Posx, this.Posy);
    }
    public Position(int x, int y)
    {
        this.Posx = x;
        this.Posy = y;
    }
}
