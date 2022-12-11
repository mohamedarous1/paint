package System;

import HelpingClasses.CircleSize;
import HelpingClasses.LWSize;
import HelpingClasses.Position;
import HelpingClasses.RegularPolygonSize;
import Shapes.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
//import org.json.*;

public class JsonConverter
{
    public JSONObject ShapeJson;
    public Shape ShapeObject;
    public JsonConverter(JSONObject obj, Shape shape)
    {
        this.ShapeJson = obj;
        this.ShapeObject = shape;
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

        double width = (double)temp;

        this.ShapeObject.SetStrokeWidth(width);
    }

    public void ExtractPosition()
    {
        Object tempx = ShapeJson.get("x");
        Object tempy = ShapeJson.get("y");

        if (tempx == null) return;

        double x = (double)tempx;
        double y = (double)tempy;

        this.ShapeObject.SetPosition(new Position(x, y));
    }

    // Waiting For Frontend
    public void ExtractSize()
    {
        ClosedShape closedShape = (ClosedShape) this.ShapeObject;
        if (this.ShapeObject instanceof Square)
        {
            double radius = (double) this.ShapeJson.get("height");
            RegularPolygonSize size = new RegularPolygonSize(radius);
            closedShape.SetSize(size);
        }
        else if (this.ShapeObject instanceof Ellipse)
        {
            double length = (double)this.ShapeJson.get("radiusX");
            double width = (double)this.ShapeJson.get("radiusY");
            LWSize size = new LWSize(length, width);
            closedShape.SetSize(size);
        }
        else if (this.ShapeObject instanceof Rectangle)
        {
            double length = (double)this.ShapeJson.get("height");
            double width = (double)this.ShapeJson.get("width");
            LWSize size = new LWSize(length, width);
            closedShape.SetSize(size);
        }
        else if (this.ShapeObject instanceof Circle)
        {
            double radius = (double) this.ShapeJson.get("radius");
            CircleSize size = new CircleSize(radius);
            closedShape.SetSize(size);
        }
        else if (this.ShapeObject instanceof RegularPolygon)
        {
            double radius = (double) this.ShapeJson.get("radius");
            RegularPolygonSize size = new RegularPolygonSize(radius);
            closedShape.SetSize(size);
        }
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

    public static String ExtractName(JSONObject jsonObject)
    {
        return (String)jsonObject.get("className");
    }

    public static Gson ExtractJson(JSONObject Data)
    {
        Object obj = Data.get("attrs");
        if (obj == null) System.out.println("nnnnnnnnn");
        System.out.println(obj);
        return (Gson) Data.get("attrs");
    }
}
