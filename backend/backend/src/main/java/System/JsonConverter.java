package System;

import HelpingClasses.*;
import Shapes.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
//import org.json.*;

public class JsonConverter
{
    public JSONObject MyJson;
    public Shape MyShape;
    public JsonConverter(JSONObject obj, Shape shape)
    {
        this.MyJson = obj;
        this.MyShape = shape;
    }

    public static void ExtractAllProperties(JSONObject MyJson, Shape MyShape)
    {
        Size size = JsonConverter.ExtractSize(MyJson, MyShape);
        String FillColor = JsonConverter.ExtractFillColor(MyJson);
        Position position = JsonConverter.ExtractPosition(MyJson);
        String StrokeColor = JsonConverter.ExtractStrokeColor(MyJson);
        double StrokeWidth = JsonConverter.ExtractStrokeWidth(MyJson);

        if (size != null)
            ((ClosedShape)MyShape).SetSize(size);
        if (FillColor != null)
            ((ClosedShape) MyShape).SetFillColor(FillColor);
        MyShape.SetPosition(position);
        MyShape.SetStrokeColor(StrokeColor);
        MyShape.SetStrokeWidth(StrokeWidth);
    }


    public static String ExtractFillColor(JSONObject ShapeJson)
    {
        Object temp = ShapeJson.get("fill");
        if (temp == null) return null;

        String color = (String)temp;
        return color;
    }

    public static String ExtractStrokeColor(JSONObject ShapeJson)
    {
        Object temp = ShapeJson.get("stroke");
        if (temp == null) return null;

        return (String)temp;
    }

    public static double ExtractStrokeWidth(JSONObject ShapeJson)
    {
        Object temp = ShapeJson.get("strokeWidth");

        double width = (double)temp;
        return width;
    }

    public static Position ExtractPosition(JSONObject ShapeJson)
    {
        Object tempx = ShapeJson.get("x");
        Object tempy = ShapeJson.get("y");

        if (tempx == null) return null;

        double x = (double)tempx;
        double y = (double)tempy;

        return new Position(x, y);
    }

    public static Size ExtractSize(JSONObject ShapeJson, Shape ShapeObject)
    {
        ClosedShape closedShape = (ClosedShape) ShapeObject;
        if (ShapeObject instanceof Square)
        {
            double radius = (double) ShapeJson.get("height");
            RegularPolygonSize size = new RegularPolygonSize(radius);
            return size;
        }
        else if (ShapeObject instanceof Ellipse)
        {
            double length = (double)ShapeJson.get("radiusX");
            double width = (double)ShapeJson.get("radiusY");
            LWSize size = new LWSize(length, width);
            return size;
        }
        else if (ShapeObject instanceof Rectangle)
        {
            double length = (double)ShapeJson.get("height");
            double width = (double)ShapeJson.get("width");
            LWSize size = new LWSize(length, width);
            return size;
        }
        else if (ShapeObject instanceof Circle)
        {
            double radius = (double) ShapeJson.get("radius");
            CircleSize size = new CircleSize(radius);
            return size;
        }
        else if (ShapeObject instanceof RegularPolygon)
        {
            double radius = (double) ShapeJson.get("radius");
            RegularPolygonSize size = new RegularPolygonSize(radius);
            return size;
        }
        return null;
    }

    public static String ExtractName(JSONObject jsonObject)
    {
        return (String)jsonObject.get("className");
    }

    public static int ExtractId(JSONObject jsonObject)
    {
        String idString = (String)jsonObject.get("id");
        int id = Integer.parseInt(idString);
        return id;
    }

    public static JSONObject GetJsoon(String jsonObject)
    {
        JSONObject NewJson = new Gson().fromJson(jsonObject , JSONObject.class);
        return NewJson;
    }
}
