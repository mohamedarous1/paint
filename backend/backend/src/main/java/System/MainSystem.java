package System;

import Operations.EnableShapeOperation;
import Operations.Operation;
import Shapes.Shape;
import com.sun.tools.javac.Main;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Stack;

public class MainSystem
{
    public static HashMap<Integer, Shape> ShapesMap = new HashMap<>();
    static Stack<Operation> OperationStack = new Stack<>();
    static Stack<Operation> OperationUndoStack = new Stack<>();
    static ShapeFactory shapeFactory = new ShapeFactory();
    private static int IDCounter = 1;

    private static int GetAndIncreamentIDCounter()
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

    public static int CreateNewObjectFront(String ShapeType, JSONObject ShapeJson)
    {
        System.out.println(ShapeJson);
        System.out.println(ShapeJson);
        System.out.println(ShapeJson);
        System.out.println(ShapeJson);
        System.out.println(ShapeJson);

        int ID = MainSystem.GetAndIncreamentIDCounter();
        Shape NewShape = MainSystem.shapeFactory.CreateShape(ID, ShapeType);
        JsonConverter jsonConverter = new JsonConverter(ShapeJson, NewShape);
        jsonConverter.ExtractAllProperties();

        Operation operation = new EnableShapeOperation(ID);
        MainSystem.PushOperationToStack(operation);
        MainSystem.InsertInShapeMap(NewShape);
        return ID;
    }




    public static Shape CreateNewObject(String ShapeType)
    {
        int newid = MainSystem.GetAndIncreamentIDCounter();

        Shape NewShape = MainSystem.shapeFactory.CreateShape(newid, ShapeType);

        MainSystem.InsertInShapeMap(NewShape);

        return NewShape;
    }
    private Shape CreateObjectOfSameClass(int ShapeId)
    {
        Shape shape = MainSystem.ShapesMap.get(ShapeId);

        int NewShapeId = MainSystem.GetAndIncreamentIDCounter();

        Shape NewShape = MainSystem.shapeFactory.CreateObjectOfSameClassAndGivenId(NewShapeId, shape);
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

}
