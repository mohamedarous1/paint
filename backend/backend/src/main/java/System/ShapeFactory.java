package System;

import Shapes.*;

public class ShapeFactory
{
    public Shape CreateShape(int ID, String ObjectString)
    {
        if (ObjectString.equals("Circle"))
            return new Circle(ID);
        else if (ObjectString.equals("Rectangle"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Triangle"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Ellipse"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Square"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Pentagon"))
            return new Rectangle(ID);
        else if (ObjectString.equals("Line"))
            return new Line(ID);

        return null;
    }

    Shape CreateObjectOfSameClassAndGivenId(int GivenId, Shape ToBeCopied)
    {
        if (ToBeCopied instanceof Circle)
            return new Circle(GivenId);
        else if (ToBeCopied instanceof Rectangle)
            return new Rectangle(GivenId);
        else if (ToBeCopied instanceof Ellipse)
            return new Ellipse(GivenId);
        else if (ToBeCopied instanceof Square)
            return new Square(GivenId);
        else if (ToBeCopied instanceof Pentagon)
            return new Pentagon(GivenId);
        else if (ToBeCopied instanceof Triangle)
            return new Triangle(GivenId);
        else if (ToBeCopied instanceof Line)
            return new Line(GivenId);

        return null;
    }
}
