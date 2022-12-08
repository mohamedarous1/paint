package Operations;

import Shapes.Shape;

public class Operation
{
    private int ShapeID;

    public int GetShapeID()
    {
        return this.ShapeID;
    }
    public Operation(int id)
    {
        this.ShapeID = id;
    }
    public void Execute(Shape shape)
    {
    }
    public Operation GetReversedOperation()
    {
        return null;
    }

}
