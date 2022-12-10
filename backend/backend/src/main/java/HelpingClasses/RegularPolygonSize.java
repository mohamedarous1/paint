package HelpingClasses;

public class RegularPolygonSize extends Size
{
    private double SideLength;
    public double GetSideLength()
    {
        return this.SideLength;
    }
    public RegularPolygonSize(double sideLength)
    {
        this.SideLength = sideLength;
    }
}
