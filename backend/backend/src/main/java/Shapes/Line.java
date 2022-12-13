package Shapes;

import HelpingClasses.MyPoint;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape
{
    ArrayList<Double> Points;

    public Line(int id)
    {
        super(id);
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
        Line line = (Line)shape;
        line.SetPointsArray(this.GetPointsArray());
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject) {
        super.PutObjectInJson(jsonObject);
        jsonObject.put("points", this.Points);
    }

    public void SetPointsArray(ArrayList<Double> GivenPoints)
    {
        this.Points = GivenPoints;
    }

    public ArrayList<Double> GetPointsArray()
    {
        return this.Points;
    }

    public ArrayList<Double> ClonePointsArray(ArrayList<Double> Oldlist)
    {
        ArrayList<Double> list = new ArrayList<>();
        for (double i : Oldlist)
            list.add(i);
        return list;
    }
}
