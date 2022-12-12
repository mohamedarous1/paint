package System;

import HelpingClasses.Position;
import HelpingClasses.Size;
import Operations.*;
import Shapes.ClosedShape;
import Shapes.Shape;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Stack;

public class MainSystem
{
    private static HashMap<Integer, Shape> ShapesMap = new HashMap<>();
    static Stack<Operation> OperationStack = new Stack<>();
    static Stack<Operation> OperationUndoStack = new Stack<>();
    static ShapeFactory ShapeFactory = new ShapeFactory();
    private static int IDCounter = 1;



    public static Operation GetAndExecuteUndoOperation()
    {
        if (MainSystem.CanMakeUndo() == false) return null;
        Operation CurrentOperation = MainSystem.OperationStack.peek();
        Operation ReversedOperation = CurrentOperation.GetReversedOperation();

        MainSystem.OperationStack.pop();
        MainSystem.OperationUndoStack.push(ReversedOperation);

        MainSystem.DoOperation(ReversedOperation);
        return ReversedOperation;
    }
    public static Operation GetAndExecuteRedoOperation()
    {
        if (MainSystem.CanMakeRedo() == false)
            return null;
        Operation CurrentOperation = MainSystem.OperationUndoStack.peek();
        Operation ReversedOperation = CurrentOperation.GetReversedOperation();

        MainSystem.OperationUndoStack.pop();
        MainSystem.OperationStack.push(ReversedOperation);

        MainSystem.DoOperation(ReversedOperation);
        return ReversedOperation;
    }

    private static int GetAndIncreamentIDCounter()
    {
        int OldIDCounter = IDCounter;
        IDCounter = IDCounter + 1;
        return OldIDCounter;
    }

    public static JSONObject GenerateJsonForFrontEnd(Operation operation)
    {
        if (operation == null)
        {
            JSONObject EmptyOperation = new JSONObject();
            EmptyOperation.put("OperationType", "EmptyOperation");
            return EmptyOperation;
        }
        JSONObject object = operation.GetJsonForFrontend();
        return object;
    }


    public static void DoOperation(Operation operation)
    {
        Shape shape = MainSystem.ShapesMap.get(operation.GetShapeID());
        operation.Execute(shape);
    }

    public static int CreateNewObjectFrontAndEmptyUndo(String ShapeType, JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = MainSystem.GetAndIncreamentIDCounter();
        Shape NewShape = MainSystem.ShapeFactory.CreateShape(ID, ShapeType);
        JsonConverter.ExtractAllProperties(ShapeJson, NewShape);

        Operation operation = new EnableShapeOperation(ID);
        MainSystem.PushOperationToStack(operation);
        MainSystem.InsertInShapeMap(NewShape);

        DoOperation(operation);
        return ID;
    }

    public static void ResizeAndChangePosition(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape Shape = MainSystem.ShapesMap.get(ID);
        ClosedShape closedShape = (ClosedShape) Shape;

        Size OldSize = closedShape.GetSize();
        Position OldPosition = closedShape.GetPosition();

        Size NewSize = JsonConverter.ExtractSize(ShapeJson, Shape);
        Position NewPosition = JsonConverter.ExtractPosition(ShapeJson);

        Operation operation = new ResizeAndChangePositionOperation
                (ID, OldPosition, NewPosition, OldSize, NewSize);
        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }

    public static void ChangePositionLineAndEmtpryUndo(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape Shape = MainSystem.ShapesMap.get(ID);
        ClosedShape closedShape = (ClosedShape) Shape;

        Position OldPosition = closedShape.GetPosition();

        Position NewPosition = JsonConverter.ExtractPosition(ShapeJson);

        ChangePositionLineOperation operation
                = new ChangePositionLineOperation(ID, OldPosition, NewPosition);
        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }

    public static void ChangeStrokeWidthAndEmtpryUndo(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape Shape = MainSystem.ShapesMap.get(ID);

        double OldWidth = Shape.GetStrokeWidth();

        double NewWidth = JsonConverter.ExtractStrokeWidth(ShapeJson);

        Operation operation = new ChangeStrokeWidthOperation(ID, OldWidth, NewWidth);

        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }

    public static void ChangeStrokeColorAndEmtpryUndo(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape Shape = MainSystem.ShapesMap.get(ID);

        String OldColor = Shape.GetStrokeColor();

        String NewColor = JsonConverter.ExtractStrokeColor(ShapeJson);

        Operation operation = new ChangeStokeColorOperation(ID, OldColor, NewColor);

        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }

    public static void ChangeFillColorAndEmtpryUndo(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        ClosedShape Shape = (ClosedShape) MainSystem.ShapesMap.get(ID);

        String OldColor = Shape.GetFillColor();

        String NewColor = JsonConverter.ExtractFillColor(ShapeJson);

        Operation operation = new ChangeFillColorOperation(ID, OldColor, NewColor);

        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }



    public static void DisableShape(JSONObject ShapeJson)
    {
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape Shape = MainSystem.ShapesMap.get(ID);

        Operation operation = new DisableShapeOperation(ID);

        MainSystem.PushOperationToStack(operation);

        DoOperation(operation);
    }

    public static Shape CreateNewObject(String ShapeType)
    {
        int newid = MainSystem.GetAndIncreamentIDCounter();

        Shape NewShape = MainSystem.ShapeFactory.CreateShape(newid, ShapeType);

        MainSystem.InsertInShapeMap(NewShape);

        return NewShape;
    }
    private Shape CreateObjectOfSameClass(int ShapeId)
    {
        Shape shape = MainSystem.ShapesMap.get(ShapeId);

        int NewShapeId = MainSystem.GetAndIncreamentIDCounter();

        Shape NewShape = MainSystem.ShapeFactory.CreateObjectOfSameClassAndGivenId(NewShapeId, shape);
        return NewShape;
    }
    public static void CloneShapeAndInsertInShapeMapp(int ShapeId)
    {

    }
    private static void InsertInShapeMap(Shape shape)
    {
        int id = shape.GetId();

        MainSystem.ShapesMap.put(id, shape);
    }
    private static void EmptyOperationUndoStack() // called when we do any operation other thatn undo and redo
    {
        while (MainSystem.OperationUndoStack.empty() == false)
            MainSystem.OperationUndoStack.pop();
    }
    private static void PushOperationToStack(Operation operation)
    {
        MainSystem.OperationStack.push(operation);
    }
    private static void PopOperationFromStack()
    {
        MainSystem.OperationStack.pop();
    }
    private static void PushOperationToUndo(Operation operation)
    {
        MainSystem.OperationUndoStack.push(operation);
    }
    private static void PopOperationFromUndo()
    {
        MainSystem.OperationUndoStack.pop();
    }
    private static boolean CanMakeUndo()
    {
        System.out.println(MainSystem.OperationStack.empty());
        System.out.println(MainSystem.OperationStack.empty());
        System.out.println(MainSystem.OperationStack.empty());
        System.out.println(MainSystem.OperationStack.empty());
        System.out.println(MainSystem.OperationStack.empty());

        if (MainSystem.OperationStack.empty()) return false;
        return true;
    }
    private static boolean CanMakeRedo()
    {
        if (MainSystem.OperationUndoStack.empty()) return false;
        return true;
    }
    public static String GetShapeType(int id)
    {
        Shape shape = MainSystem.ShapesMap.get(id);
        return MainSystem.ShapeFactory.GetShapeType(shape);
    }
}
