package System;

import Operations.Operation;
import Shapes.Shape;
import com.sun.tools.javac.Main;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Stack;

public class MainSystem
{
    static HashMap<Integer, Shape> ShapesMap = new HashMap<>();
    static Stack<Operation> OperationStack = new Stack<>();
    static Stack<Operation> OperationUndoStack = new Stack<>();
    static ShapeFactory shapeFactory = new ShapeFactory();
    private static int IDCounter = 1;

    private static int GetAndIncreamentIdCounter()
    {
        int OldIDCounter = IDCounter;
        IDCounter = IDCounter + 1;
        return OldIDCounter;
    }
    public static void Undo()
    {
        Operation CurrentOperation = MainSystem.OperationStack.peek();
        Operation ReversedOperation = CurrentOperation.GetReversedOperation();
        MainSystem.DoOperation(ReversedOperation);

        MainSystem.OperationStack.pop();
        MainSystem.OperationUndoStack.push(ReversedOperation);
    }
    public static void Redo()
    {
        Operation CurrentOperation = MainSystem.OperationUndoStack.peek();
        Operation ReversedOperation = CurrentOperation.GetReversedOperation();
        MainSystem.DoOperation(ReversedOperation);

        MainSystem.OperationUndoStack.pop();
        MainSystem.OperationStack.push(ReversedOperation);
    }

    public static void DoOperation(Operation operation)
    {
        Shape shape = MainSystem.ShapesMap.get(operation.GetShapeID());
        operation.Execute(shape);
    }

    private static void EmptyOperationUndoStack() // called when we do any operation other thatn undo and redo
    {
        while (MainSystem.OperationUndoStack.empty() == false)
            MainSystem.OperationUndoStack.pop();
    }

    public static void CreateNewObject(JSONObject object)
    {
        int shapeId = JsonConverter.GetShapeIdFromJson(object);
        String shapeType = JsonConverter.GetShapeTypeFromJson(object);

//        JsonConverter jsonConverter = new JsonConverter(object);
    }

    public static void CloneObject(int ShapeId)
    {
        Shape shape = MainSystem.ShapesMap.get(ShapeId);

    }

    public Shape CreateObjectOfSameClass(Shape shape)
    {
        int NewShapeId = MainSystem.GetAndIncreamentIdCounter();
        Shape NewShape = MainSystem.shapeFactory.CreateObjectOfSameClassAndGivenId(NewShapeId, shape);
        return NewShape;
    }
}
