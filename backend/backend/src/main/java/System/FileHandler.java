package System;


import org.json.*;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {




    public static String writeXml(String fileName, ArrayList<String> jsonArray){
        String xml = "";
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject json = new JSONObject(jsonArray.get(i));
            System.out.println(json.toString());
            String newshape = "<shape>"+XML.toString(json)+"</shape>";
            xml += newshape;
        }

        xml = "<?xml version=\"1.0\"?><root>"+xml+"</root>";
        //System.out.println(xml);
        String path = "./"+fileName;
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(xml);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return xml;
    }

    public static String readXml(String xmlStr) {

        String content = null;
        try {
            File file = new File(xmlStr);
            Scanner scanner = new Scanner(file);
            content = "";
            while (scanner.hasNextLine()) {
                content += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(content);
        System.out.println(XML.toJSONObject(content).toString());
        String s = XML.toJSONObject(content).toString();
        return XML.toJSONObject(content).toString();
    }
}
