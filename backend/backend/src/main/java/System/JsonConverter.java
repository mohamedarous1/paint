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
    public JsonConverter(JSONObject obj, Shape sh)
    {
        this.ShapeJson = obj;
        this.ShapeObject = sh;
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

    public static int GetShapeIdFromJson(JSONObject obj)
    {
        int id = (int)obj.get("id");
        return id;
    }

    public static String GetShapeTypeFromJson(JSONObject obj)
    {
        String ShapeType = (String)obj.get("ShapeType");
        return ShapeType;
    }
}
