package System;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {




    public static String writeXml(String fileName, ArrayList<String> jsonArray){
        String xml = "";
        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject json = new JSONObject(jsonArray.get(i));
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

        String content = "";

        ArrayList<org.json.simple.JSONObject> list;
        try
        {
            File file = new File(xmlStr);
            Scanner scanner = new Scanner(file);
            content = "";
            while (scanner.hasNextLine())
                content += scanner.nextLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String s = XML.toJSONObject(content).toString();
        return XML.toJSONObject(content).toString();
    }


    public static void writeJson(String fileName, ArrayList<String> jsonArray)
    {
        String path = "./"+fileName;
        try
        {
            FileWriter writer = new FileWriter(path);

//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, jsonArray);

            writer.write(jsonArray.toString());
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String readJson(String fileName)
    {
        try
        {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            String ans = "";

            while (sc.hasNextLine())
                ans += sc.nextLine();
            sc.close();

            JSONArray array = new JSONArray(ans);

            StringBuilder sb = new StringBuilder("{\"root\":{\"shape\":[");

            array.forEach((e)->{
                sb.append(e);
                sb.append(",");
            });
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]}}");
            System.out.println(sb.toString());
            System.out.println(sb.toString());
            System.out.println(sb.toString());


            return sb.toString();

//            for (array.Entry<JSONObject> set :
//                    Model.getDataBase().entrySet()) {
//                list[item++]=(undo_redo.konvaJson(set.getValue()));
//
//            }
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
