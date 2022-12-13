package HelpingClasses.Sizes;

import org.json.simple.JSONObject;

public class RectangleSize extends Size
{
    private double Height;
    private double Width;

    public RectangleSize(double height, double width)
    {
        this.Height = height;
        this.Width = width;
    }

    public RectangleSize(JSONObject jsonObject)
    {
        this.Height = (double)jsonObject.get("height");
        this.Width = (double)jsonObject.get("width");
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("height", this.Height);
        jsonObject.put("width", this.Width);
    }
}
