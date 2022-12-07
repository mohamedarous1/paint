package Shapes;

public abstract class Shape
{
    protected int Id;
    protected int StrokeWidth;
    protected String StrokeColor;
    protected boolean Enabled;

    public int GetId()
    {
        return this.Id;
    }
    public String GetStrokeColor()
    {
        return this.StrokeColor;
    }
    public void SetStrokeColor(String color)
    {
        this.StrokeColor = color;
    }
    public int GetStrokeWidth()
    {
        return this.StrokeWidth;
    }
    public void SetStrokeWidth(int width)
    {
        this.StrokeWidth = width;
    }

    public void SetEnabled(boolean b)
    {
        this.Enabled = b;
    }

}
