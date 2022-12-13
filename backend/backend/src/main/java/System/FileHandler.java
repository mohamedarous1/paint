package System;


import Shapes.Shape;
import org.json.*;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

//import static System.MainSystem.SaveShapes;

public class FileHandler
{
    public static void main(String[] args)
    {
//        System.out.println(writeXml("{id:1,name:3roos, points:[1,2,3,4]}, draggable:true"));
//        System.out.println(readXml(writeXml("{id:1,name:3roos, points:[1,2,3,4]}, draggable:true")));

    }


//    public static String writeXml(String jsonStr){
//        JSONObject json = new JSONObject(jsonStr);
//        String xml = XML.toString(json);
//        xml = "<?xml version=\"1.0\"?><root>"+xml+"</root>";
//        //System.out.println(xml);
//        String path = "./doc.xml";
//        try{
//            FileWriter writer = new FileWriter(path);
//            writer.write(xml);
//            writer.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return xml;
//    }

    public static String readXml(String xmlStr){
        return XML.toJSONObject(xmlStr).toString();
    }
}
