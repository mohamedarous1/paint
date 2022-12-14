package System;

import HelpingClasses.*;
import HelpingClasses.Sizes.*;
import Shapes.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JsonConverter
{
    public JSONObject MyJson;
    public Shape MyShape;

    public static void ExtractAllProperties(JSONObject MyJson, Shape MyShape)
    {
        Size size = JsonConverter.ExtractSize(MyJson, MyShape);
        String FillColor = JsonConverter.ExtractFillColor(MyJson);
        Position position = JsonConverter.ExtractPosition(MyJson);
        String StrokeColor = JsonConverter.ExtractStrokeColor(MyJson);
        double StrokeWidth = JsonConverter.ExtractStrokeWidth(MyJson);
        Scale scale = JsonConverter.ExtractScale(MyJson);


        ArrayList<Double> list = JsonConverter.ExtractPointsArrayForLine(MyJson);


        if (list != null)
        {
            Line line = (Line) MyShape;
            line.SetPointsArray(list);
        }

        MyShape.SetScale(scale);
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

    public static Scale ExtractScale(JSONObject jsonObject)
    {
        return new Scale(jsonObject);
    }

    public static Size ExtractSize(JSONObject shapeJson, Shape shape)
    {
        if (shape instanceof Circle)
            return new CircleSize(shapeJson);
        else if (shape instanceof Rectangle)
            return new RectangleSize(shapeJson);
        else if (shape instanceof Square)
            return new SquareSize(shapeJson);
        else if (shape instanceof Ellipse)
            return new EllipseSize(shapeJson);
        else if (shape instanceof Triangle)
            return new TriangleSize(shapeJson);
        else
        {
            System.out.print("Not Supposed to Reach here (JsonConverter.ExtractSize");
            return null;
        }
    }

    public static int ExtractId(JSONObject jsonObject)
    {
        String idString = (String)jsonObject.get("id");
        for(int i = 0; i < 5; i++){
            System.out.println(idString);

        }
        int id = Integer.parseInt(idString);
        return id;
    }

    public static ArrayList<Double> ExtractPointsArrayForLine(JSONObject jsonObject)
    {
        ArrayList<Double> list = (ArrayList<Double>) jsonObject.get("points");
        return list;
    }

    public static JSONObject GetJson(String jsonObject)
    {
        System.out.println(jsonObject);
        System.out.println(jsonObject);
        System.out.println(jsonObject);
        System.out.println(jsonObject);

        JSONObject NewJson = new Gson().fromJson(jsonObject , JSONObject.class);
        return NewJson;
    }

}
