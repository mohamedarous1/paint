package HelpingClasses;
import System.Convertable;
import org.json.simple.JSONObject;

import java.awt.*;

public class Position implements Convertable
{
    private double Posx;
    private double Posy;

    public MyPoint GetPosition()
    {
        return new MyPoint(this.Posx, this.Posy);
    }
    public Position(double x, double y)
    {
        this.Posx = x;
        this.Posy = y;
    }

    @Override
    public void ConvertObjectToJson(JSONObject jsonObject)
    {
        jsonObject.put("x", this.Posx);
        jsonObject.put("y", this.Posy);
    }

    public Position(JSONObject jsonObject)
    {
        this.Posx = (double)jsonObject.get("x");
        this.Posy = (double)jsonObject.get("y");
    }
}
