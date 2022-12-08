package Operations;


import Shapes.Shape;

public class ChangeStokeColorOperation extends Operation
{
    private String OldColor;
    private String NewColor;

    public ChangeStokeColorOperation(int id, String prevcolor, String newcolor)
    {
        super(id);
        this.OldColor = prevcolor;
        this.NewColor = newcolor;
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetStrokeColor(this.NewColor);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangeStokeColorOperation ReversedOperation
                = new ChangeStokeColorOperation(this.GetShapeID(), this.NewColor, this.OldColor);
        return ReversedOperation;
    }


}
