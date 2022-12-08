package Operations;


import Shapes.Shape;

public class ChangeStrokeWidthOperation extends Operation
{
    private int OldStrokeWidth;
    private int NewStrokeWidth;

    public ChangeStrokeWidthOperation(int id, int prevwidth, int newwidth)
    {
        super(id);
        this.OldStrokeWidth = prevwidth;
        this.NewStrokeWidth = newwidth;
    }

    @Override
    public void Execute(Shape shape)
    {
        shape.SetStrokeWidth(this.NewStrokeWidth);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangeStrokeWidthOperation ReversedOperation
                = new ChangeStrokeWidthOperation(this.GetShapeID(), this.NewStrokeWidth, this.OldStrokeWidth);
        return ReversedOperation;
    }

}
