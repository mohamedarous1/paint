package Shapes;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape
{
    ArrayList<Point> Points;

    public Line(int id)
    {
        super(id);
    }

    public void SetPointsArray(ArrayList<Point> GivenPoints)
    {
        this.Points = GivenPoints;
    }

    public ArrayList<Point> GetPointsArray()
    {
        return this.Points;
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
        Line line = (Line)shape;
        line.SetPointsArray(this.GetPointsArray());
    }
}
