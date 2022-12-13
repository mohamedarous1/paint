package HelpingClasses;

import org.json.simple.JSONObject;

public class RegularPolygonSize extends Size
{
    private double SideLength;
    public double GetSideLength()
    {
        return this.SideLength;
    }
    public RegularPolygonSize(double sideLength)
    {
        this.SideLength = sideLength;
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        JSONObject Newobj = new JSONObject();

        Newobj.put("side", this.SideLength);
    }

    public RegularPolygonSize(JSONObject jsonObject)
    {
        System.out.println(jsonObject);
        System.out.println(jsonObject);
        System.out.println(jsonObject);

        if (jsonObject.containsKey("side"))
            this.SideLength = (double)jsonObject.get("side");
        else if (jsonObject.containsKey("radius"))
            this.SideLength = (double)jsonObject.get("radius");
    }
}
