package HelpingClasses.Sizes;

import org.json.simple.JSONObject;

public class TriangleSize extends Size
{
    private double Radius;

    public TriangleSize(double radius)
    {
        this.Radius = radius;
    }

    public TriangleSize(JSONObject jsonObject)
    {
        this.Radius = (double)jsonObject.get("radius");
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("radius", this.Radius);
    }
}
