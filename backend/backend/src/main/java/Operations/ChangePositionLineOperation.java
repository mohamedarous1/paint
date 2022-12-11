package Operations;

import HelpingClasses.MyPoint;
import HelpingClasses.Position;
import Shapes.ClosedShape;
import Shapes.Shape;
import org.json.simple.JSONObject;

public class ChangePositionLineOperation extends Operation
{
    private Position OldPosition;
    private Position NewPosition;

    public ChangePositionLineOperation(int id, Position prevposition, Position newposition)
    {
        super(id);
        this.OldPosition = prevposition;
        this.NewPosition = newposition;
    }

    @Override
    public void Execute(Shape shape)
    {
        ClosedShape closedshape = (ClosedShape) shape;

        closedshape.SetPosition(this.NewPosition);
    }
    @Override
    public Operation GetReversedOperation()
    {
        ChangePositionLineOperation ReversedOperation
                = new ChangePositionLineOperation
                (this.GetShapeID(), this.NewPosition, this.OldPosition);
        return ReversedOperation;
    }

    @Override
    public JSONObject GetJsonForFrontend()
    {
        JSONObject jsonObject = super.GetJsonForFrontend();
        jsonObject.put("OperationType", "ChangeStokeColorOperation");
        MyPoint point = NewPosition.GetPosition();
        jsonObject.put("x", point.Getx());
        jsonObject.put("y", point.Gety());

        return jsonObject;
    }
}
