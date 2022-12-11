package System;

import Shapes.*;

import java.io.Console;

public class ShapeFactory
{
    public Shape CreateShape(int ID, String ObjectString)
    {
        if (ObjectString.equals("Circle"))
            return new Circle(ID);
        else if (ObjectString.equals("Rectangle"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Triangle"))
            return new Triangle(ID);
        else if (ObjectString.equals("Ellipse"))
            return new Ellipse(ID);
        else if (ObjectString.equals("Square"))
            return new Square(ID);
        else if (ObjectString.equals("Pentagon"))
            return new Pentagon(ID);
        else if (ObjectString.equals("Line"))
            return new Line(ID);

        return null;
    }

    Shape CreateObjectOfSameClassAndGivenId(int GivenId, Shape ToBeCopied)
    {
        String ShapeType = this.GetShapeType(ToBeCopied);
        return this.CreateShape(GivenId, ShapeType);
    }

    String GetShapeType(Shape shape)
    {
        if (shape instanceof Circle)
            return "Circle";
        else if (shape instanceof Rectangle)
            return "Rectangle";
        else if (shape instanceof Ellipse)
            return "Ellipse";
        else if (shape instanceof Square)
            return "Square";
        else if (shape instanceof Pentagon)
            return "Pentagon";
        else if (shape instanceof Triangle)
            return "Triangle";
        else if (shape instanceof Line)
            return "Line";

        return "No Type Corresponds to this shape";
    }
}
