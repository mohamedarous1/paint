package Operations;

import Shapes.Shape;

public class EnableShapeOperation extends Operation
{
    public EnableShapeOperation(int id)
    {
        super(id);
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetEnabled();
    }
    @Override
    public Operation GetReversedOperation()
    {
        DisableShapeOperation ReversedOperation
                = new DisableShapeOperation(this.GetShapeID());
        return ReversedOperation;
    }
}
