package HelpingClasses;

import org.json.simple.JSONObject;

public class CircleSize extends Size
{
    private double Radius;
    public double GetRadius()
    {
        return this.Radius;
    }
    public CircleSize(double radius)
    {
        this.Radius = radius;
    }

    @Override
    public void ConvertObjectToJson(JSONObject jsonObject)
    {
        jsonObject.put("radius", this.Radius);
    }


    public CircleSize(JSONObject jsonObject)
    {
        this.Radius = (double)jsonObject.get("radius");
    }
}
