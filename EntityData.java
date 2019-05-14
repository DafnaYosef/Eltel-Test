import java.util.ArrayList;
import java.util.List;

public class EntityData {
	
	private String entityID; 
	private String name; 
	private String color; 
	private String shape; 
	private String size; 
	private String xLocation;
	private String yLocation;
	private Coordinates locationXY;
	private List<String> dimension;
	private boolean isVisible; 
	private boolean isBlocked;
	
	static public List<EntityData> listEntityData= new ArrayList<EntityData>();
	static final int MAX_ENTITY = 10;

	
	   // constructor
	   public EntityData(String entityID, String name, String color, String shape, String size, String xLocation, String yLocation) {
		  this.entityID = entityID;
	      this.name = name;
	      this.color = color;
	      this.shape = shape;
	      this.size = size;
	      this.xLocation = xLocation;
	      this.yLocation = yLocation;
	      this.locationXY = new Coordinates(xLocation, yLocation);
	      this.dimension = new  ArrayList<String>();
	      this.dimension.add(this.xLocation + "," + this.yLocation);
	      this.isVisible = true;
	      this.isBlocked = false;

	   }

	       // getter
	       public String getEntityID() { return entityID; }
	       public String getName() { return name; }
	       public String getColor() { return color; }
	       public String getShape() { return shape; }
	       public String getSize() { return size; }
	       public String getXLocation() { return xLocation; }
	       public String getYLocation() { return yLocation; }
	       public Coordinates getLocationXY() { return locationXY; }
	       public List<String> getDimension() { return dimension; }
	       public boolean getIsVisible() { return isVisible; }
	       public boolean getIsBlocked() { return isBlocked; }


	       // setter
	       public void setEntityID(String entityID) { this.entityID = entityID; }
	       public void setName(String name) { this.name = name; }
	       public void setColor(String color) { this.color = color; }
	       public void setShape(String shape) { this.shape = shape; }
	       public void setSize(String size) { this.size = size; }
	       public void setXLocation(String xLocation) { this.xLocation = xLocation; }
	       public void setYLocation(String yLocation) { this.yLocation = yLocation; }
	       public void setLocationXY(Coordinates locationXY) { this.locationXY = locationXY; }
	       public void setDimension(List<String> dimension) { this.dimension = dimension; }
	       public void setIsVisible(boolean isVisible) { this.isVisible = isVisible; }
	       public void setIsBlocked(boolean isBlocked) { this.isBlocked = isBlocked; }




}
