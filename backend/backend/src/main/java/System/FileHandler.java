package System;


import Shapes.Shape;
import org.json.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

//import static System.MainSystem.SaveShapes;

public class FileHandler
{
    public static String writeXml(ArrayList<String> jsonArray){
        String xml = "";
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject json = new JSONObject(jsonArray.get(i));
            System.out.println(json.toString());
            xml += XML.toString(json);
        }

        xml = "<?xml version=\"1.0\"?><root>"+xml+"</root>";
        //System.out.println(xml);
        String path = "./doc.xml";
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(xml);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return xml;
    }
    public static String readXml(String xmlStr){
        return XML.toJSONObject(xmlStr).toString();
    }
}
