package HelpingClasses.Sizes;

import org.json.simple.JSONObject;

public class SquareSize extends Size
{
    private double SideLength;

    public SquareSize(double sideLength)
    {
        this.SideLength = sideLength;
    }

    public SquareSize(JSONObject jsonObject)
    {
        this.SideLength = (double)jsonObject.get("height");
        this.SideLength = (double)jsonObject.get("width");
        // second line is not important as height = width(from front end)
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("height", this.SideLength);
        jsonObject.put("width", this.SideLength);
    }
}
