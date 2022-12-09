package Shapes;

public class ShapeFactory
{
    public Shape CreateShape(String ObjectString, int id)
    {
        if (ObjectString.equals("Circle"))
        {
            return new Circle();
        }
        return null;
    }
}
