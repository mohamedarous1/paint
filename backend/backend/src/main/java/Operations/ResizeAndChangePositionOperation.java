package Operations;

import HelpingClasses.*;
import Shapes.ClosedShape;
import Shapes.Shape;

import java.awt.*;

public class ResizeAndChangePositionOperation extends Operation
{
    private Size OldSize;
    private Size NewSize;
    private Position OldPosition;
    private Position NewPosition;

    public ResizeAndChangePositionOperation
            (int id, Position prevposition, Position newposition, Size oldsize, Size newsize)
    {
        super(id);
        this.OldPosition = prevposition;
        this.NewPosition = newposition;
        this.OldSize = oldsize;
        this.NewSize = newsize;
    }

    @Override
    public void Execute(Shape shape)
    {
        ClosedShape closedShape = (ClosedShape) shape;

        Point newposition = this.NewPosition.GetPosition();

        closedShape.SetPosition(newposition.x, newposition.y);
        closedShape.SetSize(NewSize);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ResizeAndChangePositionOperation ReversedOperation
                = new ResizeAndChangePositionOperation
                (this.GetShapeID(), this.NewPosition, this.OldPosition, this.NewSize, this.OldSize);
        return ReversedOperation;
    }
}
