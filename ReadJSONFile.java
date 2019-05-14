import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONFile {
	public static void parserFile() {
		JSONParser parser = new JSONParser();
        try {     
        	 Object object = parser.parse(new FileReader("entityData.json"));         
        	 JSONArray  jsonArr = (JSONArray) object; 
        	 for (int i = 0 ; i< jsonArr.size(); i++) {
             	JSONObject jsonObject  = (JSONObject)jsonArr.get(i);
             	
	            String entity_ID = (String) jsonObject.get("entity_ID");
	            System.out.println("entity_ID: " + entity_ID);
	
	            String name = (String) jsonObject.get("name");
	            System.out.println("name: " + name);
	
	            String color = (String) jsonObject.get("color");
	            System.out.println("color: " + color);
	            
	            String shape = (String) jsonObject.get("shape");
	            System.out.println("shape: " + shape);
	
	            String size = (String) jsonObject.get("size");
	            System.out.println("size: " + size);
	            
	            String x = (String) jsonObject.get("X");
	            System.out.println("x: " + x);
	            
	            String y = (String) jsonObject.get("Y");
	            System.out.println("y: " + y);
	            EntityData.listEntityData.add(new EntityData(entity_ID, name, color, shape, size, x, y));
         	 }
        	 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
        e.printStackTrace();
    } 
	}

}
