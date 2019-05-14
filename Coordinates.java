
public class Coordinates {
	private String xLocation; 
	private String yLocation; 
	
	   // constructor
	   public Coordinates(String xLocation, String yLocation) {
		      this.xLocation = xLocation;
		      this.yLocation = yLocation;	   
	   }
	   
       // getter
       public String getXLocation() { return xLocation; }
       public String getYLocation() { return yLocation; }
     
       // setter
       public void setXLocation(String xLocation) { this.xLocation = xLocation; }
       public void setYLocation(String yLocation) { this.yLocation = yLocation; }
}
