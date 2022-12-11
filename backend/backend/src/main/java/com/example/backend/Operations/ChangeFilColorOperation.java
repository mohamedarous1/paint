package Operations;

import Shapes.ClosedShape;
import Shapes.Shape;

public class ChangeFilColorOperation extends Operation
{
    private String OldColor;
    private String NewColor;

    public ChangeFilColorOperation(int id, String prevcolor, String newcolor)
    {
        super(id);
        this.OldColor = prevcolor;
        this.NewColor = newcolor;
    }

    @Override
    public void Execute(Shape shape)
    {
        ((ClosedShape)shape).SetFillColor(this.NewColor);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangeFilColorOperation ReversedOperation
                = new ChangeFilColorOperation(this.GetShapeID(), this.NewColor, this.OldColor);
        return ReversedOperation;
    }
}
