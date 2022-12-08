package Shapes;

public abstract class Shape implements Cloneable
{
    protected int ID;
    protected int StrokeWidth;
    protected String StrokeColor;
    protected boolean Enabled;

    public int GetId()
    {
        return this.ID;
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
    public void SetEnabled()
    {
        this.Enabled = true;
    }
    public void SetDisabled()
    {
        this.Enabled = false;
    }

    @Override
    protected Object clone()
    {
        Shape shape = GetSimilarShapeType();

        shape.ID = this.ID;
        shape.StrokeColor = this.StrokeColor;
        shape.StrokeWidth = this.StrokeWidth;
        shape.Enabled = this.Enabled;

        return shape;
    }

    public Shape GetSimilarShapeType()
    {
        if (this instanceof Circle)
        {
            return new Circle();
        }
        else if (this instanceof Rectangle)
        {
            return new Rectangle();
        }
        else if (this instanceof Ellipse)
        {
            return new Ellipse();
        }
        else if (this instanceof Square)
        {
            return new Square();
        }
        else if (this instanceof Pentagon)
        {
            return new Pentagon();
        }
        else if (this instanceof Triangle)
        {
            return new Triangle();
        }
        return null;
    }
}
