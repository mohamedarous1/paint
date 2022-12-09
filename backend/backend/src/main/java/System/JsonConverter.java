package System;

import HelpingClasses.Position;
import Shapes.ClosedShape;
import Shapes.Shape;
import org.json.simple.JSONObject;
//import org.json.*;

public class JsonConverter
{
    public JSONObject ShapeJson;
    public Shape ShapeObject;
    public JsonConverter(JSONObject obj)
    {
        this.ShapeJson = obj;
    }

    public void ExtractAllProperties()
    {
        this.ExtractSize();
        this.ExtractFillColor();
        this.ExtractPosition();
        this.ExtractStrokeColor();
        this.ExtractStrokeWidth();
    }

    public void ExtractFillColor()
    {
        Object temp = ShapeJson.get("FillColor");
        if (temp == null) return;

        String color = (String)temp;
        ClosedShape closedshape = (ClosedShape)this.ShapeObject;

        closedshape.SetFillColor(color);
    }

    public void ExtractStrokeColor()
    {
        Object temp = ShapeJson.get("StrokeColor");
        if (temp == null) return;

        String color = (String)temp;

        this.ShapeObject.SetStrokeColor(color);
    }

    public void ExtractStrokeWidth()
    {
        Object temp = ShapeJson.get("StrokeWidth");
        if (temp == null) return;

        int width = (int)temp;

        this.ShapeObject.SetStrokeWidth(width);
    }

    public void ExtractPosition()
    {
        Object tempx = ShapeJson.get("x");
        Object tempy = ShapeJson.get("y");

        if (tempx == null) return;

        int x = (int)tempx;
        int y = (int)tempy;

        this.ShapeObject.SetPosition(x, y);
    }

    // Waiting For Frontend
    public void ExtractSize()
    {

    }

}
