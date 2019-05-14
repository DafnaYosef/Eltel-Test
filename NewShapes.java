import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

 public class NewShapes extends JPanel{
	
	
	public void paintComponent(Graphics g)	{
		for (int i = 0; i < EntityData.listEntityData.size() && i < EntityData.MAX_ENTITY; i++) {
			String color = EntityData.listEntityData.get(i).getColor();
			String size =  EntityData.listEntityData.get(i).getSize();
			String shape = EntityData.listEntityData.get(i).getShape();
			String xLocation = EntityData.listEntityData.get(i).getXLocation();
			String yLocation = EntityData.listEntityData.get(i).getYLocation();
			String entityID = "elD" + EntityData.listEntityData.get(i).getEntityID();
		    
			Color  entityColor = getEntityColor(color);
			if (entityColor == Color.BLACK)
				System.out.println("Invalide Entity Color");
				
			int entitySize = getEntitySize(size);
			if (entitySize == 0)
				System.out.println("Invalide Entity Size");
			
			entityColor = (EntityData.listEntityData.get(i).getIsVisible() ? entityColor: Color.LIGHT_GRAY);
			createEntity((Integer.parseInt(xLocation)*3), (Integer.parseInt(yLocation)*3), entitySize, entityColor, shape, g, entityID);
		}
	}
	
	//Get the entity color
	private Color getEntityColor(String color) {

		if (color.equalsIgnoreCase("red"))
			return Color.RED;
		else if (color.equalsIgnoreCase("blue"))
			return Color.BLUE;
		else if (color.equalsIgnoreCase("green"))
			return Color.GREEN;
		else return Color.BLACK;
		
	}
	
	//Get the entity size
	private int getEntitySize(String size) {
		if (size.equalsIgnoreCase("small"))
			return 6;
		else if (size.equalsIgnoreCase("medium"))
			return 12;
		else if (size.equalsIgnoreCase("large"))
			return 18;
		else return 0;
	}
	
	//Create the relevant shape
	private void createEntity(int xLocation, int yLocation, int size, Color color, String shape, Graphics g, String entityID) {
		g.setColor(color);		
	    g.drawString(entityID, xLocation, yLocation);

		if (shape.equalsIgnoreCase("square")) {
		    g.fillRect(xLocation, yLocation ,size, size);
		}		
		else if (shape.equalsIgnoreCase("circle")) {
		    g.fillOval(xLocation, yLocation ,size, size);
		}
		else if (shape.equalsIgnoreCase("triangle")) {
			g.fillPolygon(new int[] {xLocation, xLocation, xLocation+size}, new int[] {yLocation, yLocation+size, yLocation+size}, 3);
		}
	}
 
 }
	

