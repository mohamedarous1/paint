package Operations;

import Shapes.Shape;

public class DisableShapeOperation extends Operation
{
    public DisableShapeOperation(int id)
    {
        super(id);
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetDisabled();
    }
    @Override
    public Operation GetReversedOperation()
    {
        EnableShapeOperation ReversedOperation
                = new EnableShapeOperation(this.GetShapeID());
        return ReversedOperation;
    }

}
