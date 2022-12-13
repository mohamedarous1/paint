package HelpingClasses;

import org.json.simple.JSONObject;

import java.awt.*;

public class LWSize extends Size
{
    private double Length;
    private double Width;
    public MyPoint GetLWSize()
    {
        return new MyPoint(this.Length, this.Width);
    }
    public LWSize(double lenth, double width)
    {
        this.Length = lenth;
        this.Width = width;
    }

    @Override
    public void ConvertObjectToJson(JSONObject jsonObject)
    {
        jsonObject.put("length", this.Length);
        jsonObject.put("width", this.Width);
    }

    public LWSize(JSONObject jsonObject)
    {
        if (jsonObject.containsKey("length"))
            this.Length = (double)jsonObject.get("length");
        else if (jsonObject.containsKey("radiusY"))
            this.Length = (double)jsonObject.get("radiusY");
        else if (jsonObject.containsKey("height"))
            this.Length = (double)jsonObject.get("height");

        if (jsonObject.containsKey("width"))
            this.Width = (double)jsonObject.get("width");
        else if (jsonObject.containsKey("radiusX"))
            this.Width = (double)jsonObject.get("radiusX");
        else if (jsonObject.containsKey("width"))
            this.Width = (double)jsonObject.get("width");
    }
}
