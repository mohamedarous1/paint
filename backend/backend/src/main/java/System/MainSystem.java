package System;

import HelpingClasses.Position;
import HelpingClasses.Scale;
import HelpingClasses.Sizes.Size;
import Operations.*;
import Shapes.ClosedShape;
import Shapes.Shape;
import com.sun.tools.javac.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;




import org.json.*;
import org.json.simple.parser.JSONParser;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSystem
{
    private static HashMap<Integer, Shape> ShapesMap = new HashMap<>();
    static Stack<Operation> OperationStack = new Stack<>();
    static Stack<Operation> OperationUndoStack = new Stack<>();
    static ShapeFactory ShapeFactory = new ShapeFactory();
    private static int IDCounter = 1;

    public static void ClearAllShapes()
    {
        MainSystem.IDCounter = 1;
        MainSystem.ShapesMap = new HashMap<>();
        MainSystem.OperationStack = new Stack<>();
        MainSystem.OperationUndoStack = new Stack<>();
        MainSystem.ShapeFactory = new ShapeFactory();
    }


    public static ArrayList<String> SaveShapes()
    {
        ArrayList<String> Shapes = new ArrayList<>();
        ShapesMap.forEach( (key, value)->{
            //verified
            //System.out.println(value.toString());
            JSONObject jsonObject = new JSONObject();
            //error in this line
            value.PutObjectInJson(jsonObject);

            Shapes.add(jsonObject.toJSONString());
        } );
        return Shapes;
    }

    public void loadjson(String fileName)
    {
        String Content = "";
        JSONObject obj;

        ArrayList<JSONObject> list;
        try
        {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);


            while (sc.hasNextLine())
                Content += sc.nextLine();

            sc.close();

            JSONParser parser = new JSONParser();

            obj = (JSONObject)parser.parse(Content);

            list = (ArrayList<JSONObject>) ((JSONObject)obj.get("root") ).get("shape");
            System.out.println(list);
            System.out.println(list);
            System.out.println(list);
            System.out.println(list);

            System.out.println("nhhhhhhh");
            System.out.println("nhhhhhhh");
            System.out.println("nhhhhhhh");
            System.out.println("nhhhhhhh");


            MainSystem.ClearAllShapes();

            for (JSONObject json : list)
            {
                String ShapeType = (String) json.get("ShapeType");
                String id = (String) json.get("ShapeType");
                System.out.println(id);System.out.println(id);System.out.println(id);System.out.println(id);System.out.println(id);
                MainSystem.CreateNewShapeFrontAndEmptyUndo(ShapeType, json);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }


//    public static void func(String content)
//    {
//        try
//        {
//            JSONObject obj = JsonConverter.GetJson(content);
//
//
//            ArrayList<JSONObject> list = (ArrayList<JSONObject>) ((JSONObject) obj.get("root")).get("shape");
//
//            MainSystem.ClearAllShapes();
//
//            for (JSONObject json : obj)
//            {
//
//            }
//
////            for (org.json.simple.JSONObject json : list)
////            {
////                String ShapeType = (String) json.get("ShapeType");
////                String id = (String)json.get("id");
////                System.out.println(id);System.out.println(id);System.out.println(id);System.out.println(id);System.out.println(id);System.out.println(id);
////                MainSystem.CreateNewShapeFrontAndEmptyUndo(ShapeType, json);
////            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }


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

    public static int CreateNewShapeFrontAndEmptyUndo(String ShapeType, JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = MainSystem.GetAndIncreamentIDCounter();

        ShapeJson.put("id", Integer.toString(ID));

        Shape NewShape = MainSystem.ShapeFactory.CreateShape(ID, ShapeType);
        JsonConverter.ExtractAllProperties(ShapeJson, NewShape);

        Operation operation = new EnableShapeOperation(ID);
        MainSystem.PushOperationToStack(operation);
        MainSystem.InsertInShapeMap(NewShape);

        DoOperation(operation);


        return ID;
    }

    public static void RescaleAndChangePosition(JSONObject ShapeJson)
    {
        EmptyOperationUndoStack();
        int ID = JsonConverter.ExtractId(ShapeJson);

        Shape shape = MainSystem.ShapesMap.get(ID);


        Scale OldScale = shape.GetScale();
        Scale NewScale = JsonConverter.ExtractScale(ShapeJson);

        Position OldPosition = shape.GetPosition();
        Position NewPosition = JsonConverter.ExtractPosition(ShapeJson);

        Operation operation = new RescaleAndChangePositionOperation
                (ID, OldPosition, NewPosition, OldScale, NewScale);
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

    public static Size GetShapeSizeById(int id)
    {
        Shape shape = MainSystem.ShapesMap.get(id);
        ClosedShape closedShape = (ClosedShape) shape;

        return closedShape.GetSize();
    }

    public static Position GetShapePositionById(int id)
    {
        Shape shape = MainSystem.ShapesMap.get(id);
        return shape.GetPosition();
    }

    static Shape GetShapeById(int id)
    {
        return MainSystem.ShapesMap.get(id);
    }
}
