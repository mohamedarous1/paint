package System;

import Shapes.*;

public class ShapeTypesFunctions
{
    public static boolean isRegularPolygon(int ShapeId)
    {
        Shape shape = MainSystem.GetShapeById(ShapeId);
        if (shape instanceof RegularPolygon)
            return true;
        else
            return false;
    }

    public static boolean Circle(int ShapeId)
    {
        Shape shape = MainSystem.GetShapeById(ShapeId);
        if (shape instanceof Circle)
            return true;
        else
            return false;
    }

    public static boolean isRectangle(int ShapeId)
    {
        Shape shape = MainSystem.GetShapeById(ShapeId);
        if (shape instanceof Rectangle)
            return true;
        else
            return false;
    }

    public static boolean isEllipse(int ShapeId)
    {
        Shape shape = MainSystem.GetShapeById(ShapeId);
        if (shape instanceof Ellipse)
            return true;
        else
            return false;
    }

    public static boolean isCircle(int ShapeId)
    {
        Shape shape = MainSystem.GetShapeById(ShapeId);

        System.out.println(shape);
        System.out.println(shape);
        System.out.println(shape);
        System.out.println(shape);


        if (shape instanceof Circle)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
