package Shapes;

import HelpingClasses.Sizes.Size;
import org.json.simple.JSONObject;

public abstract class ClosedShape extends Shape
{
    private String FilColor;
    protected Size MySize;

    public ClosedShape(int id)
    {
        super(id);
    }

    @Override
    public void Clone(Shape shape)
    {
        super.Clone(shape);
        ClosedShape ToBeCloned = (ClosedShape)shape;

        ToBeCloned.SetFillColor( this.GetFillColor());
        ToBeCloned.SetSize(this.GetSize());
    }

    @Override
    public void PutObjectInJson(JSONObject jsonObject)
    {
        if (this.MySize == null)
        {
            System.out.println("Error MySize PutinJson");
            System.out.println("Error MySize PutinJson");
            System.out.println("Error MySize PutinJson");
            System.out.println("Error MySize PutinJson");
            System.out.println("Error MySize PutinJson");
        }
        super.PutObjectInJson(jsonObject);
        jsonObject.put("fill", this.FilColor);
        this.MySize.PutObjectInJson(jsonObject);
    }

    public void SetFillColor(String color)
    {
        this.FilColor = color;
    }
    public String GetFillColor()
    {
        return this.FilColor;
    }

    public abstract void SetSize(Size s);

    public Size GetSize()
    {
        return this.MySize;
    }

}
