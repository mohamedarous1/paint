package HelpingClasses.Sizes;

import org.json.simple.JSONObject;

public class EllipseSize extends Size
{
    private double RadiusX;
    private double RadiusY;

    public EllipseSize(double radiusX, double radiusY)
    {
        this.RadiusX = radiusX;
        this.RadiusY = radiusY;
    }

    public EllipseSize(JSONObject jsonObject)
    {
        this.RadiusX = (double)jsonObject.get("radiusX");
        this.RadiusY = (double)jsonObject.get("radiusY");
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("radiusX", this.RadiusX);
        jsonObject.put("radiusY", this.RadiusY);
    }
}
