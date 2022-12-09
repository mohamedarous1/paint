package Operations;

import HelpingClasses.Position;
import HelpingClasses.Size;
import Shapes.ClosedShape;
import Shapes.Shape;

import java.awt.*;

public class ResizeLineOperation extends Operation
{
    private Position OldPosition;
    private Position NewPosition;

    public ResizeLineOperation
            (int id, Position prevposition, Position newposition)
    {
        super(id);
        this.OldPosition = prevposition;
        this.NewPosition = newposition;
    }

    @Override
    public void Execute(Shape shape)
    {
        ClosedShape closedshape = (ClosedShape) shape;

        Point newposition = this.NewPosition.GetPosition();

        closedshape.SetPosition(newposition.x, newposition.y);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ResizeLineOperation ReversedOperation
                = new ResizeLineOperation
                (this.GetShapeID(), this.NewPosition, this.OldPosition);
        return ReversedOperation;
    }
}
