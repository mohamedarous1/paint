package HelpingClasses.Sizes;

import HelpingClasses.Sizes.Size;
import org.json.simple.JSONObject;

public class CircleSize extends Size
{
    private double Radius;

    public CircleSize(double radius)
    {
        this.Radius = radius;
    }

    public CircleSize(JSONObject jsonObject)
    {
        this.Radius = (double)jsonObject.get("radius");
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("radius", this.Radius);
    }
}
