package System;

import Shapes.*;

public class ShapeFactory
{
    public Shape CreateShape(String ObjectString, int id)
    {
        if (ObjectString.equals("Circle"))
        {
            return new Circle(id);
        }
        return null;
    }


    Shape CreateObjectOfSameClassAndGivenId(int GivenId, Shape ToBeCopied)
    {
        if (ToBeCopied instanceof Circle)
            return new Circle(GivenId);
        else if (ToBeCopied instanceof Rectangle)
            return new Rectangle();
        else if (ToBeCopied instanceof Ellipse)
            return new Ellipse();
        else if (ToBeCopied instanceof Square)
            return new Square();
        else if (ToBeCopied instanceof Pentagon)
            return new Pentagon();
        else if (ToBeCopied instanceof Triangle)
            return new Triangle();

        return null;
    }
}
