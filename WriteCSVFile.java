import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteCSVFile {
	public static void csvWriter (int userInputNum) {
	    try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

	        StringBuilder sb = new StringBuilder();
	        // Header
	        sb.append("entityID");
	        sb.append(',');
	        sb.append("Name");
	        sb.append(",");
	        sb.append("X");
	        sb.append(",");
	        sb.append("Y");
	        sb.append('\n');
	        
	        // build the file Data according to the user input number (userInputNum)
	    	for (int i=0; i<EntityData.listEntityData.size() && i < EntityData.MAX_ENTITY ; i++) {
	    		String entityID = "elD" + EntityData.listEntityData.get(i).getEntityID();
	    		String name = EntityData.listEntityData.get(i).getName();
    			System.out.println("For " + entityID + " the maximum existing location: " + EntityData.listEntityData.get(i).getDimension().size());
	    		for (int j=0 ; j<EntityData.listEntityData.get(i).getDimension().size() && j <userInputNum; j++) {
	    			String location = EntityData.listEntityData.get(i).getDimension().get(j) ;
	    	        sb.append(entityID);
	    	        sb.append(',');
	    	        sb.append(name);
	    	        sb.append(",");
	    	        sb.append(location);
	    	        sb.append('\n');
	    		}
	    	}
	        writer.write(sb.toString());

	      } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	      }

	    }
}
