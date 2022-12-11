package System;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class XMLConverter {

    //mock array of hashmaps
    private HashMap<String, String>[] objects;

    public XMLConverter(){

        //initializing the mock hashmap
        this.objects = new HashMap[4];

        for(int i = 0; i < 4; i++){
            this.objects[i] = new HashMap<>();
        }

        this.objects[0].put("id", "circle123");
        this.objects[0].put("radius", "3");
        this.objects[0].put("fill", "yellow");

        this.objects[1].put("id", "rectangle123");
        this.objects[1].put("length", "5");
        this.objects[1].put("width", "2");
        this.objects[1].put("fill", "green");

        this.objects[2].put("id", "square123");
        this.objects[2].put("side-length", "5");
        this.objects[2].put("fill", "blue");

        this.objects[3].put("id", "ellipse123");
        this.objects[3].put("length", "5");
        this.objects[3].put("width", "9");
        this.objects[3].put("fill", "black");

    }

//    public static void main(String[] args){
//        System.out.println(Arrays.toString(new XMLConverter().objects));
//        writeXML("doc.xml", new XMLConverter().objects);
//        System.out.println(Arrays.toString(readXML("./doc.xml")));
//    }


    private static void addAttribute(Document doc, Element element, String attrName, String value){
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(value);
        element.setAttributeNode(attr);
    }


    public static void writeXML(String fileName, HashMap<String, String>[] objects){
//        //TODO add fileName as param instead of "doc.xml"
//        String fileName = "doc.xml";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.newDocument();

            //root element of document
            Element root = xmlDoc.createElement("root");
            xmlDoc.appendChild(root);

            for(HashMap<String,String> map: objects){
                Element element = xmlDoc.createElement("shape");
                map.forEach((key, value)-> addAttribute(xmlDoc, element, key, value));
                root.appendChild(element);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(new FileOutputStream("./"+fileName));

            transformer.transform(source, result);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, String>[] readXML(String filePath){
//        //TODO add filePath as a parameter
//        String filePath = "./doc.xml";
        ArrayList<HashMap<String, String>> objList = new ArrayList<>();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(new File(filePath));
            Element root = xmlDoc.getDocumentElement();

            NodeList list = root.getChildNodes();


            for(int i = 0; i < list.getLength(); i++){
                Node node = list.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    NamedNodeMap attrList = element.getAttributes();

                    HashMap<String, String> map = new HashMap<>();

                    for(int j = 0; j < attrList.getLength(); j++){
                        Node attr = attrList.item(j);
                        map.put(attr.getNodeName(), attr.getNodeValue());
                    }
                    objList.add(map);

                }

            }
            HashMap<String,String>[] objectsArr = new HashMap[objList.size()];
            return objList.toArray(objectsArr);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
