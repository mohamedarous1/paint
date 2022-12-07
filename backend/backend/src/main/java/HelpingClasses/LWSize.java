package HelpingClasses;

import java.awt.*;

public class LWSize extends Size
{
    private int Length;
    private int Width;
    public Point GetLWSize()
    {
        return new Point(this.Length, this.Width);
    }
}
