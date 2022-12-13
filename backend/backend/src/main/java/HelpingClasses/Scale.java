package HelpingClasses;

import org.json.simple.JSONObject;
import System.Convertable;

public class Scale implements Convertable
{
    private double ScaleX;
    private double ScaleY;

    public Scale(double x, double y)
    {
        this.ScaleX = x;
        this.ScaleY = y;
    }


    public Scale(JSONObject jsonObject)
    {
        this.ScaleX = (double)jsonObject.get("scaleX");
        this.ScaleY = (double)jsonObject.get("scaleY");
    }

    public void SetScale(double x, double y)
    {
        this.ScaleX = x;
        this.ScaleY = y;
    }

    public MyPoint GetScale()
    {
        return new MyPoint(this.ScaleX, this.ScaleY);
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        jsonObject.put("scaleX", this.ScaleX);
        jsonObject.put("scaleY", this.ScaleY);
    }
}
