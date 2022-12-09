package System;

import Operations.Operation;
import Shapes.Shape;
import Shapes.ShapeFactory;
import com.sun.tools.javac.Main;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MainSystem
{
    static HashMap<Integer, Shape> ShapesMap = new HashMap<>();
    static Stack<Operation> OperationStack = new Stack<>();
    static Stack<Operation> OperationUndoStack = new Stack<>();
    static ShapeFactory shapeFactory = new ShapeFactory();

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

    public static void ReceiveFrontJsonObject(JSONObject object)
    {

    }

    public static void CreateAndInitializeShape(JSONObject object)
    {

    }
}
