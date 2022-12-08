package Operations;

import Shapes.Shape;

public class Operation
{
    private int ShapeID;

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
